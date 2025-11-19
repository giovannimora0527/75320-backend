package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Session;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.repository.SessionRepository;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.security.JwtUtil;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.CifrarService;
import com.uniminuto.clinica.service.LoginIntentoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AutenticarServiceImpl implements AutenticarService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private LoginIntentoService loginIntentoService;
    
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private CifrarService cifrarService;
    
    @Autowired
    private SessionRepository sessionRepository;
    
    @Value("${security.login.max-intentos:3}")
    private int maxIntentosFallidos;
    
    @Value("${security.login.tiempo-bloqueo-minutos:5}")
    private int tiempoBloqueoMinutos;
    
    @Override
    @Transactional
    public AutenticatorRs autenticar(AuthenticatorRq request, String ip)
            throws BadRequestException {
        
        // Buscar usuario
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(request.getUsername());
        if (usuarioOpt.isEmpty()) {
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        // Verificar si la cuenta está bloqueada
        if (isCuentaBloqueada(usuario)) {
            String tiempoRestante = getTiempoRestanteBloqueado(usuario);
            loginIntentoService.registrarIntento(usuario, ip, false);
            throw new BadRequestException(
                "Cuenta bloqueada temporalmente. Intente nuevamente en " + tiempoRestante
            );
        }
        
        // Verificar si la cuenta está activa
        if (!usuario.isActivo()) {
            loginIntentoService.registrarIntento(usuario, ip, false);
            throw new BadRequestException("Cuenta inactiva. Contacte al administrador.");
        }
        
        // Validar contraseña
        boolean passwordOk;
        if (passwordEncoder != null) {
            passwordOk = passwordEncoder.matches(request.getPassword(), usuario.getPassword());
        } else {
            passwordOk = usuario.getPassword().equals(
                this.cifrarService.encriptarPassword(request.getPassword())
            );
        }
        
        if (!passwordOk) {
            // Incrementar intentos fallidos
            Integer intentosActuales = usuario.getIntentosFallidos();
            if (intentosActuales == null) {
                intentosActuales = 0;
            }
            intentosActuales++;
            usuario.setIntentosFallidos(intentosActuales);
            
            // Registrar intento fallido
            loginIntentoService.registrarIntento(usuario, ip, false);
            
            // Verificar si debe bloquearse la cuenta
            if (intentosActuales >= maxIntentosFallidos) {
                bloquearCuenta(usuario);
                usuarioRepository.save(usuario);
                throw new BadRequestException(
                    "Cuenta bloqueada por múltiples intentos fallidos. " +
                    "Intente nuevamente en " + tiempoBloqueoMinutos + " minutos."
                );
            }
            
            // Guardar intentos actualizados
            usuarioRepository.save(usuario);
            
            int intentosRestantes = maxIntentosFallidos - intentosActuales;
            throw new BadRequestException(
                "Usuario o contraseña incorrectos. Intentos restantes: " + intentosRestantes
            );
        }
        
        // Login exitoso - Resetear intentos fallidos
        resetearIntentosFallidos(usuario);
        usuarioRepository.save(usuario);
        
        // Registrar intento exitoso
        loginIntentoService.registrarIntento(usuario, ip, true);
        
        // Generar token JWT
        String token = jwtUtil.generateToken(usuario);
        
        // Crear sesión
        crearSesionUsuario(usuario, token);
        
        // Preparar respuesta
        AutenticatorRs rta = new AutenticatorRs();
        rta.setToken(token);
        
        return rta;
    }
    
    private boolean isCuentaBloqueada(Usuario usuario) {
        if (usuario.getCuentaBloqueadaHasta() == null) {
            return false;
        }
        
        LocalDateTime ahora = LocalDateTime.now();
        
        // Si ya pasó el tiempo de bloqueo, desbloquear automáticamente
        if (ahora.isAfter(usuario.getCuentaBloqueadaHasta())) {
            resetearIntentosFallidos(usuario);
            usuarioRepository.save(usuario);
            return false;
        }
        
        return true;
    }
    
    private void resetearIntentosFallidos(Usuario usuario) {
        usuario.setIntentosFallidos(0);
        usuario.setCuentaBloqueadaHasta(null);
    }
    
    private void bloquearCuenta(Usuario usuario) {
        LocalDateTime bloqueoHasta = LocalDateTime.now().plusMinutes(tiempoBloqueoMinutos);
        usuario.setCuentaBloqueadaHasta(bloqueoHasta);
    }
    
    private String getTiempoRestanteBloqueado(Usuario usuario) {
        if (usuario.getCuentaBloqueadaHasta() == null) {
            return "0 segundos";
        }
        
        LocalDateTime ahora = LocalDateTime.now();
        Duration duracion = Duration.between(ahora, usuario.getCuentaBloqueadaHasta());
        
        if (duracion.isNegative()) {
            return "0 segundos";
        }
        
        long minutos = duracion.toMinutes();
        long segundos = duracion.minusMinutes(minutos).getSeconds();
        
        if (minutos > 0) {
            return minutos + " minuto(s) y " + segundos + " segundo(s)";
        } else {
            return segundos + " segundo(s)";
        }
    }
    
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