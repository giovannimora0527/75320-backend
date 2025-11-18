package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.AuditoriaLoginService;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.CifrarService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniminuto.clinica.entity.Session;
import com.uniminuto.clinica.repository.SessionRepository;
import com.uniminuto.clinica.security.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

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
    private AuditoriaLoginService auditoriaLoginService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional
    public AutenticatorRs autenticar(AuthenticatorRq authenticatorRq) throws BadRequestException {
        String ipAddress = obtenerDireccionIPv4();

        // Verificar si el usuario está bloqueado
        if (auditoriaLoginService.usuarioEstaBloqueado(authenticatorRq.getUsername())) {
            String mensaje = "Login temporalmente bloqueado por múltiples intentos fallidos. Intente nuevamente en " +
                    obtenerTiempoRestanteBloqueo(authenticatorRq.getUsername()) + " minutos.";
            auditoriaLoginService.registrarIntentoLogin(
                    authenticatorRq.getUsername(),
                    ipAddress,
                    false,
                    "Intento de login con usuario bloqueado"
            );
            throw new BadRequestException(mensaje);
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(authenticatorRq.getUsername());
        if (usuarioOpt.isEmpty()) {
            auditoriaLoginService.registrarIntentoLogin(
                    authenticatorRq.getUsername(),
                    ipAddress,
                    false,
                    "Intento fallido: Usuario no encontrado"
            );
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.isActivo()) {
            auditoriaLoginService.registrarIntentoLogin(
                    authenticatorRq.getUsername(),
                    ipAddress,
                    false,
                    "Intento fallido: Usuario inactivo"
            );
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        boolean passwordOk;
        if (passwordEncoder != null) {
            passwordOk = passwordEncoder.matches(authenticatorRq.getPassword(), usuario.getPassword());
        } else {
            passwordOk = usuario.getPassword().equals(this.cifrarService.encriptarPassword(authenticatorRq.getPassword()));
        }

        if (!passwordOk) {
            auditoriaLoginService.registrarIntentoLogin(
                    authenticatorRq.getUsername(),
                    ipAddress,
                    false,
                    "Intento fallido: Contraseña incorrecta - Intentos: " + (usuario.getIntentosFallidos() + 1)
            );
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        // Login exitoso
        auditoriaLoginService.registrarIntentoLogin(
                authenticatorRq.getUsername(),
                ipAddress,
                true,
                "Login exitoso"
        );

        // Generar y devolver un JWT
        AutenticatorRs rta = new AutenticatorRs();
        String token = jwtUtil.generateToken(usuario);
        rta.setToken(token);

        // Creamos la sesión del usuario autenticado
        crearSesionUsuario(usuario, token);
        return rta;
    }

    /**
     * Obtiene la dirección IPv4 del cliente
     */
    private String obtenerDireccionIPv4() {
        String ipAddress = request.getHeader("X-Forwarded-For");

        // Si hay múltiples IPs (proxies), tomar la primera
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // Convertir IPv6 localhost a IPv4
        if ("0:0:0:0:0:0:0:1".equals(ipAddress) || "::1".equals(ipAddress)) {
            ipAddress = "127.0.0.1";
        }

        // Si es IPv6, intentar extraer IPv4 embebida (formato ::ffff:192.168.1.1)
        if (ipAddress != null && ipAddress.contains(":")) {
            // Buscar formato IPv4 embebida en IPv6
            if (ipAddress.contains(".")) {
                String[] parts = ipAddress.split(":");
                for (String part : parts) {
                    if (part.contains(".")) {
                        // Validar que sea una IPv4 válida
                        if (esDireccionIPv4Valida(part)) {
                            ipAddress = part;
                            break;
                        }
                    }
                }
            }

            // Si aún es IPv6 y estamos en localhost, usar 127.0.0.1
            if (ipAddress.contains(":") && (ipAddress.startsWith("0:") || ipAddress.startsWith("::"))) {
                ipAddress = "127.0.0.1";
            }
        }

        return ipAddress;
    }

    /**
     * Verifica si una cadena es una dirección IPv4 válida
     */
    private boolean esDireccionIPv4Valida(String ip) {
        try {
            if (ip == null || ip.isEmpty()) {
                return false;
            }

            String[] parts = ip.split("\\.");
            if (parts.length != 4) {
                return false;
            }

            for (String part : parts) {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            }

            return !ip.endsWith(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Calcula el tiempo restante de bloqueo en minutos
     */
    private long obtenerTiempoRestanteBloqueo(String username) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getFechaDesbloqueo() != null) {
                LocalDateTime ahora = LocalDateTime.now();
                long minutosRestantes = java.time.Duration.between(ahora, usuario.getFechaDesbloqueo()).toMinutes();
                return minutosRestantes > 0 ? minutosRestantes : 1;
            }
        }
        return 1;
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