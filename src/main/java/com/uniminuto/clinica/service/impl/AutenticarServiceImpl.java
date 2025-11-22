package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Session;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.*;
import com.uniminuto.clinica.repository.SessionRepository;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.security.JwtUtil;
import com.uniminuto.clinica.service.AuditoriaService;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.CifrarService;
import com.uniminuto.clinica.service.EmailService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AutenticarServiceImpl implements AutenticarService {

    private static final Logger LOG = Logger.getLogger(AutenticarServiceImpl.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CifrarService cifrarService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SeguridadConfig seguridadConfig;

    @Override
    @Transactional
    public AutenticatorRs autenticar(AuthenticatorRq request, String ip) throws BadRequestException {
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getUsername());
        
        // Verificar si el usuario existe
        if (usuarioOpt.isEmpty()) {
            auditoriaService.registrarIntentoFallidoLogin(
                    request.getUsername(),
                    "Usuario no existe",
                    ip
            );
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        Usuario usuario = usuarioOpt.get();

        // Verificar si la cuenta está bloqueada
        if (estaBloqueado(usuario)) {
            long minutosRestantes = calcularMinutosRestantesBloqueo(usuario);
            auditoriaService.registrarIntentoFallidoLogin(
                    request.getUsername(),
                    "Intento de acceso con cuenta bloqueada. Tiempo restante: " + minutosRestantes + " minutos",
                    ip
            );
            throw new BadRequestException("Cuenta bloqueada temporalmente. Intente nuevamente en " 
                    + minutosRestantes + " minutos.");
        }

        // Verificar contraseña
        boolean passwordOk = verificarPassword(request.getPassword(), usuario);
        
        if (!passwordOk) {
            // Incrementar intentos fallidos
            incrementarIntentosFallidos(usuario);
            
            auditoriaService.registrarIntentoFallidoLogin(
                    request.getUsername(),
                    "Contraseña incorrecta. Intento " + usuario.getIntentosFallidos() 
                            + " de " + seguridadConfig.getIntentosMaximos(),
                    ip
            );

            // Verificar si debe bloquearse
            if (usuario.getIntentosFallidos() >= seguridadConfig.getIntentosMaximos()) {
                bloquearUsuario(usuario);
                auditoriaService.registrarBloqueo(
                        request.getUsername(),
                        "Cuenta bloqueada por " + seguridadConfig.getMinutosBloqueo() 
                                + " minutos debido a " + seguridadConfig.getIntentosMaximos() 
                                + " intentos fallidos consecutivos",
                        ip
                );
                throw new BadRequestException("Cuenta bloqueada por múltiples intentos fallidos. "
                        + "Intente nuevamente en " + seguridadConfig.getMinutosBloqueo() + " minutos.");
            }

            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        // Login exitoso - Resetear intentos fallidos
        resetearIntentosFallidos(usuario);

        // Generar token
        AutenticatorRs rta = new AutenticatorRs();
        String token = jwtUtil.generateToken(usuario);
        rta.setToken(token);

        // Crear sesión
        crearSesionUsuario(usuario, token);
        
        LOG.info(String.format("Login exitoso - Usuario: %s, IP: %s", usuario.getUsername(), ip));
        
        return rta;
    }

    @Override
    @Transactional
    public RespuestaRs recuperarContrasena(RecuperarPasswordRq request, String ip)
            throws BadRequestException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        // Siempre devolver esta respuesta por seguridad
        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Si el usuario existe, se ha enviado un correo con instrucciones "
                + "para recuperar la contraseña.");

        // Usuario no existe
        if (usuarioOpt.isEmpty()) {
            auditoriaService.registrarRecuperacionFallida(
                    request.getEmail(),
                    "Usuario no existe en el sistema",
                    ip
            );
            LOG.warning(String.format("Intento de recuperación con usuario inválido: %s desde IP: %s",
                    request.getEmail(), ip));

            return respuesta;
        }

        Usuario usuario = usuarioOpt.get();

        // Usuario sin email registrado
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            auditoriaService.registrarRecuperacionFallida(
                    request.getEmail(),
                    "Usuario no tiene correo electrónico registrado",
                    ip
            );
            LOG.warning(String.format("Usuario %s no tiene email registrado", request.getEmail()));
            return respuesta;
        }

        // Generar contraseña temporal
        String passwordTemporal = generarPasswordTemporal();
        String passwordTemporalEncriptada = cifrarService.encriptarPassword(passwordTemporal);

        // Guardar datos de la contraseña temporal
        usuario.setPasswordTemporal(passwordTemporalEncriptada);
        usuario.setFechaExpiracionTemp(LocalDateTime.now()
                .plusHours(seguridadConfig.getHorasExpiracionPasswordTemporal()));
        usuarioRepository.save(usuario);

        // Construir email
        String asunto = "Recuperación de Contraseña - Sistema Clínica";
        String cuerpoHtml = construirEmailRecuperacion(usuario.getUsername(), passwordTemporal);

        // ---- ENVÍO DE CORREO CON MANEJO DE EXCEPCIÓN ----
        try {
            emailService.sendHtmlEmail(
                    usuario.getEmail(),
                    asunto,
                    cuerpoHtml,
                    emailService.getTo()
            );
        } catch (Exception ex) {

            auditoriaService.registrarRecuperacionFallida(
                    request.getEmail(),
                    "Error enviando correo: " + ex.getMessage(),
                    ip
            );

            LOG.severe("Error enviando correo de recuperación: " + ex.getMessage());

            // Importante: NO lanzamos excepción → evita /error
            return respuesta;
        }

        // Registrar éxito en auditoría
        auditoriaService.registrarRecuperacionExitosa(request.getEmail(), ip);

        LOG.info(String.format("Recuperación de contraseña exitosa - Usuario: %s, IP: %s",
                usuario.getUsername(), ip));

        return respuesta;
    }


    /**
     * Verifica la contraseña del usuario (normal o temporal).
     */
    private boolean verificarPassword(String passwordIngresada, Usuario usuario) {
        // Primero verificar si hay contraseña temporal válida
        if (usuario.getPasswordTemporal() != null && usuario.getFechaExpiracionTemp() != null) {
            if (LocalDateTime.now().isBefore(usuario.getFechaExpiracionTemp())) {
                String passTemporalEncriptada = cifrarService.encriptarPassword(passwordIngresada);
                if (usuario.getPasswordTemporal().equals(passTemporalEncriptada)) {
                    // Contraseña temporal válida - actualizar a contraseña principal
                    usuario.setPassword(passTemporalEncriptada);
                    usuario.setPasswordTemporal(null);
                    usuario.setFechaExpiracionTemp(null);
                    usuarioRepository.save(usuario);
                    return true;
                }
            } else {
                // Contraseña temporal expirada
                usuario.setPasswordTemporal(null);
                usuario.setFechaExpiracionTemp(null);
                usuarioRepository.save(usuario);
            }
        }

        // Verificar contraseña normal
        if (passwordEncoder != null) {
            return passwordEncoder.matches(passwordIngresada, usuario.getPassword());
        } else {
            return usuario.getPassword().equals(cifrarService.encriptarPassword(passwordIngresada));
        }
    }

    /**
     * Verifica si un usuario está bloqueado.
     */
    private boolean estaBloqueado(Usuario usuario) {
        if (usuario.getFechaBloqueo() == null) {
            return false;
        }
        
        LocalDateTime finBloqueo = usuario.getFechaBloqueo()
                .plusMinutes(seguridadConfig.getMinutosBloqueo());
        
        if (LocalDateTime.now().isBefore(finBloqueo)) {
            return true;
        }
        
        // El bloqueo ha expirado, limpiar datos
        usuario.setFechaBloqueo(null);
        usuario.setIntentosFallidos(0);
        usuarioRepository.save(usuario);
        return false;
    }

    /**
     * Calcula los minutos restantes de bloqueo.
     */
    private long calcularMinutosRestantesBloqueo(Usuario usuario) {
        if (usuario.getFechaBloqueo() == null) {
            return 0;
        }
        
        LocalDateTime finBloqueo = usuario.getFechaBloqueo()
                .plusMinutes(seguridadConfig.getMinutosBloqueo());
        
        long minutosRestantes = java.time.Duration.between(LocalDateTime.now(), finBloqueo).toMinutes();
        return Math.max(0, minutosRestantes);
    }

    /**
     * Incrementa los intentos fallidos del usuario.
     */
    private void incrementarIntentosFallidos(Usuario usuario) {
        if (usuario.getIntentosFallidos() == null) {
            usuario.setIntentosFallidos(0);
        }
        usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
        usuarioRepository.save(usuario);
    }

    /**
     * Bloquea al usuario temporalmente.
     */
    private void bloquearUsuario(Usuario usuario) {
        usuario.setFechaBloqueo(LocalDateTime.now());
        usuarioRepository.save(usuario);
        LOG.warning(String.format("Usuario %s bloqueado por %d minutos", 
                usuario.getUsername(), seguridadConfig.getMinutosBloqueo()));
    }

    /**
     * Resetea los intentos fallidos después de login exitoso.
     */
    private void resetearIntentosFallidos(Usuario usuario) {
        if (usuario.getIntentosFallidos() != null && usuario.getIntentosFallidos() > 0) {
            usuario.setIntentosFallidos(0);
            usuario.setFechaBloqueo(null);
            usuarioRepository.save(usuario);
        }
    }

    /**
     * Genera una contraseña temporal aleatoria.
     */
    private String generarPasswordTemporal() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 12; i++) {
            password.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        
        return password.toString();
    }

    /**
     * Construye el HTML del email de recuperación.
     */
    private String construirEmailRecuperacion(String username, String passwordTemporal) {
        return "<html><body style='font-family: Arial, sans-serif;'>" +
                "<div style='max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;'>" +
                "<h2 style='color: #333;'>Recuperación de Contraseña</h2>" +
                "<p>Hola <strong>" + username + "</strong>,</p>" +
                "<p>Has solicitado recuperar tu contraseña. Tu contraseña temporal es:</p>" +
                "<div style='background-color: #f5f5f5; padding: 15px; margin: 20px 0; " +
                "border-left: 4px solid #007bff; font-size: 18px; font-weight: bold;'>" +
                passwordTemporal +
                "</div>" +
                "<p><strong>Importante:</strong></p>" +
                "<ul>" +
                "<li>Esta contraseña temporal es válida por " + 
                seguridadConfig.getHorasExpiracionPasswordTemporal() + " horas.</li>" +
                "<li>Al iniciar sesión con esta contraseña, se convertirá en tu contraseña principal.</li>" +
                "<li>Si no solicitaste este cambio, por favor contacta al administrador.</li>" +
                "</ul>" +
                "<p style='margin-top: 30px; color: #666; font-size: 12px;'>" +
                "Este es un correo automático, por favor no responder." +
                "</p>" +
                "</div></body></html>";
    }

    /**
     * Crea y almacena la sesión del usuario autenticado.
     */
    private void crearSesionUsuario(Usuario usuario, String token) {
        sessionRepository.deleteByUserId(usuario.getId().intValue());
        Session session = new Session();
        session.setUserId(usuario.getId().intValue());
        session.setToken(token);
        session.setFechaIniSesion(LocalDateTime.now());
        Date fechaExpiracion = jwtUtil.getExpirationDateFromToken(token);
        session.setFechaExpiracion(fechaExpiracion.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime());
        sessionRepository.save(session);
    }
}