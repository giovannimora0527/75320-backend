package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.LoginIntentoLog;
import com.uniminuto.clinica.entity.Session;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.repository.LoginIntentoLogRepository;
import com.uniminuto.clinica.repository.SessionRepository;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.security.JwtUtil;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.CifrarService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private LoginIntentoLogRepository loginIntentoLogRepository;

    @Value("${seguridad.login.max-intentos}")
    private int maxIntentos;

    @Value("${seguridad.login.bloqueo-minutos}")
    private int minutosBloqueo;

    @Override
    @Transactional
    public AutenticatorRs autenticar(AuthenticatorRq request, String ipOrigen)
            throws BadRequestException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(request.getUsername());

        // Si no existe el usuario, registramos intento fallido genérico
        if (usuarioOpt.isEmpty()) {
            registrarIntento(request.getUsername(), ipOrigen, false,
                    "Intento de inicio de sesión con usuario inexistente");
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        Usuario usuario = usuarioOpt.get();
        LocalDateTime ahora = LocalDateTime.now();

        // 1. Validar si está bloqueado temporalmente
        if (usuario.getBloqueadoHasta() != null && ahora.isBefore(usuario.getBloqueadoHasta())) {
            String msg = "Usuario bloqueado hasta " + usuario.getBloqueadoHasta();
            registrarIntento(usuario.getUsername(), ipOrigen, false,
                    "Intento de login mientras usuario bloqueado. " + msg);
            throw new BadRequestException(msg);
        }

        // 2. Validar contraseña
        boolean passwordOk;
        if (passwordEncoder != null) {
            passwordOk = passwordEncoder.matches(request.getPassword(), usuario.getPassword());
        } else {
            passwordOk = usuario.getPassword()
                    .equals(this.cifrarService.encriptarPassword(request.getPassword()));
        }

        if (!passwordOk) {
            // Intento fallido -> incrementar contador
            int nuevosIntentos = (usuario.getIntentosFallidos() == null ? 0 : usuario.getIntentosFallidos()) + 1;
            usuario.setIntentosFallidos(nuevosIntentos);

            String descripcion = "Intento fallido " + nuevosIntentos + " para el usuario.";

            // Si llegó al máximo, lo bloqueamos
            if (nuevosIntentos >= maxIntentos) {
                LocalDateTime bloqueadoHasta = ahora.plusMinutes(minutosBloqueo);
                usuario.setBloqueadoHasta(bloqueadoHasta);
                descripcion += " Usuario bloqueado hasta " + bloqueadoHasta;
            }

            usuarioRepository.save(usuario);
            registrarIntento(usuario.getUsername(), ipOrigen, false, descripcion);

            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        // 3. Login correcto -> resetear contador y desbloquear
        usuario.setIntentosFallidos(0);
        usuario.setBloqueadoHasta(null);
        usuarioRepository.save(usuario);

        registrarIntento(usuario.getUsername(), ipOrigen, true, "Login exitoso");

        // 4. Generar y devolver JWT
        AutenticatorRs rta = new AutenticatorRs();
        String token = jwtUtil.generateToken(usuario);
        rta.setToken(token);

        // Creamos la sesión del usuario autenticado
        crearSesionUsuario(usuario, token);
        return rta;
    }

    @Transactional
    public AutenticatorRs autenticar(AuthenticatorRq request)
            throws BadRequestException {
        return autenticar(request, null);
    }

    private void registrarIntento(String username, String ipOrigen, boolean exito, String descripcion) {
        LoginIntentoLog log = new LoginIntentoLog();
        log.setFechaHora(LocalDateTime.now());
        log.setUsername(username);
        log.setDescripcion(descripcion);
        log.setIpOrigen(ipOrigen);
        log.setExito(exito);
        loginIntentoLogRepository.save(log);
    }

    /**
     * Crea y almacena la sesión del usuario autenticado.
     *
     * @param usuario Usuario autenticado
     * @param token   Token JWT generado
     */
    private void crearSesionUsuario(Usuario usuario, String token) {
        // Elimina cualquier sesión previa del usuario (adaptar si el repo usa Long/Integer)
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
