package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.entity.AuditoriaLogin;
import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.repository.AuditoriaLoginRepository;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.CifrarService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import com.uniminuto.clinica.entity.Session;
import com.uniminuto.clinica.repository.SessionRepository;
import com.uniminuto.clinica.security.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class AutenticarServiceImpl implements AutenticarService {

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
    private AuditoriaLoginRepository auditoriaLoginRepository;
    
    @Value("${security.login.block.duration.minutes:5}")
    private int blockDurationMinutes;
    
    @Value("${security.login.max.failed.attempts:3}")
    private int maxFailedAttempts;

    @Override
    @Transactional
    public AutenticatorRs autenticar(AuthenticatorRq request, String ipOrigen)
            throws BadRequestException {
        
        String username = request.getUsername() != null ? request.getUsername().toLowerCase() : "";
        LocalDateTime now = LocalDateTime.now();
        
        // Buscar usuario (case-insensitive como en otros servicios)
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        
        // Si el usuario no existe, registrar intento fallido y lanzar excepción
        if (usuarioOpt.isEmpty()) {
            registrarIntentoFallido(username, "Usuario no encontrado", ipOrigen, null);
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        // Verificar si el usuario está bloqueado
        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isAfter(now)) {
            long minutosRestantes = java.time.Duration.between(now, usuario.getBloqueadoHasta()).toMinutes();
            registrarIntentoFallido(username, "Intento de login con usuario bloqueado. Tiempo restante: " + minutosRestantes + " minutos", ipOrigen, usuario.getId());
            throw new BadRequestException("Usuario bloqueado temporalmente. Intente nuevamente en " + minutosRestantes + " minutos.");
        }
        
        // Si el bloqueo expiró, resetear los intentos fallidos
        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isBefore(now)) {
            usuario.setBloqueadoHasta(null);
            usuario.setIntentosFallidos(0);
        }
        
        // Validar contraseña
        boolean passwordOk;
        if (passwordEncoder != null) {
            passwordOk = passwordEncoder.matches(request.getPassword(), usuario.getPassword());
        } else {
            passwordOk = usuario.getPassword().equals(this.cifrarService.encriptarPassword(request.getPassword()));
        }
        
        if (!passwordOk) {
            // Incrementar intentos fallidos
            int intentosActuales = usuario.getIntentosFallidos() != null ? usuario.getIntentosFallidos() : 0;
            intentosActuales++;
            usuario.setIntentosFallidos(intentosActuales);
            
            // Si alcanzó el máximo de intentos, bloquear usuario
            if (intentosActuales >= maxFailedAttempts) {
                usuario.setBloqueadoHasta(now.plusMinutes(blockDurationMinutes));
                registrarIntentoFallido(username, "Usuario bloqueado después de " + intentosActuales + " intentos fallidos. Bloqueado por " + blockDurationMinutes + " minutos", ipOrigen, usuario.getId());
                usuarioRepository.save(usuario);
                
                System.out.println("==========================================");
                System.out.println("BLOQUEO DE USUARIO POR INTENTOS FALLIDOS");
                System.out.println("==========================================");
                System.out.println("Usuario: " + username);
                System.out.println("Intentos fallidos: " + intentosActuales);
                System.out.println("Bloqueado hasta: " + usuario.getBloqueadoHasta());
                System.out.println("IP Origen: " + (ipOrigen != null ? ipOrigen : "No disponible"));
                System.out.println("Fecha/Hora: " + now);
                System.out.println("==========================================");
                
                throw new BadRequestException("Usuario bloqueado temporalmente por " + blockDurationMinutes + " minutos debido a múltiples intentos fallidos.");
            } else {
                registrarIntentoFallido(username, "Contraseña incorrecta. Intentos fallidos: " + intentosActuales + "/" + maxFailedAttempts, ipOrigen, usuario.getId());
                usuarioRepository.save(usuario);
            }
            
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }
        
        // Login exitoso: resetear intentos fallidos y desbloquear si estaba bloqueado
        usuario.setIntentosFallidos(0);
        usuario.setBloqueadoHasta(null);
        usuarioRepository.save(usuario);
        
        // Registrar intento exitoso
        registrarIntentoExitoso(username, ipOrigen, usuario.getId());
        
        // Generar y devolver un JWT
        AutenticatorRs rta = new AutenticatorRs();
        String token = jwtUtil.generateToken(usuario);
        rta.setToken(token);

        // Creamos la sesión del usuario autenticado
        crearSesionUsuario(usuario, token);
        return rta;
    }
    
    /**
     * Registra un intento fallido de login en la auditoría.
     */
    private void registrarIntentoFallido(String username, String descripcion, String ipOrigen, Long usuarioId) {
        AuditoriaLogin auditoria = new AuditoriaLogin();
        auditoria.setUsernameIngresado(username);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setExitoso(false);
        auditoria.setDescripcion(descripcion);
        auditoria.setIpOrigen(ipOrigen);
        auditoria.setUsuarioId(usuarioId);
        auditoriaLoginRepository.save(auditoria);
        
        System.out.println("==========================================");
        System.out.println("AUDITORÍA DE LOGIN - INTENTO FALLIDO");
        System.out.println("==========================================");
        System.out.println("Usuario: " + username);
        System.out.println("Fecha/Hora: " + auditoria.getFechaHora());
        System.out.println("IP Origen: " + (ipOrigen != null ? ipOrigen : "No disponible"));
        System.out.println("Descripción: " + descripcion);
        System.out.println("==========================================");
    }
    
    /**
     * Registra un intento exitoso de login en la auditoría.
     */
    private void registrarIntentoExitoso(String username, String ipOrigen, Long usuarioId) {
        AuditoriaLogin auditoria = new AuditoriaLogin();
        auditoria.setUsernameIngresado(username);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setExitoso(true);
        auditoria.setDescripcion("Login exitoso");
        auditoria.setIpOrigen(ipOrigen);
        auditoria.setUsuarioId(usuarioId);
        auditoriaLoginRepository.save(auditoria);
        
        System.out.println("==========================================");
        System.out.println("AUDITORÍA DE LOGIN - INTENTO EXITOSO");
        System.out.println("==========================================");
        System.out.println("Usuario: " + username);
        System.out.println("Fecha/Hora: " + auditoria.getFechaHora());
        System.out.println("IP Origen: " + (ipOrigen != null ? ipOrigen : "No disponible"));
        System.out.println("==========================================");
    }

    /**
     * Crea y almacena la sesión del usuario autenticado.
     *
     * @param usuario Usuario autenticado
     * @param token   Token JWT generado
     */
    private void crearSesionUsuario(Usuario usuario, String token) {
        // Elimina cualquier sesión previa del usuario
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
