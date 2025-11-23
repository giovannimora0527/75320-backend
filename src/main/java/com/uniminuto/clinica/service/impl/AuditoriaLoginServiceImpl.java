/**
 * Implementación del servicio de auditoría de login.
 * Gestiona el registro de intentos de acceso, control de bloqueos por intentos fallidos
 * y el reinicio de contadores de seguridad para usuarios del sistema clínico.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.config.LoginConfig;
import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.repository.RecuperarRepository;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.AuditoriaLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class AuditoriaLoginServiceImpl implements AuditoriaLoginService {

    @Autowired
    private RecuperarRepository recuperarRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginConfig loginConfig;

    /**
     * Registra un intento de login en el sistema y actualiza el estado del usuario
     * según el resultado del intento. Para intentos fallidos, incrementa el contador
     * y bloquea al usuario si excede el límite configurado.
     *
     * @param username Nombre de usuario que intentó el acceso
     * @param ipAddress Dirección IP desde donde se realizó el intento
     * @param exitoso true si el login fue exitoso, false si falló
     * @param descripcion Descripción detallada del resultado del intento
     */
    @Override
    public void registrarIntentoLogin(String username, String ipAddress, boolean exitoso, String descripcion) {
        RecuperarPasswordAuditoria auditoria = new RecuperarPasswordAuditoria(username, descripcion, ipAddress);
        auditoria.setTipoAuditoria("LOGIN");
        recuperarRepository.save(auditoria);

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (exitoso) {
                usuario.setIntentosFallidos(0);
                usuario.setBloqueado(false);
                usuario.setFechaBloqueo(null);
                usuario.setFechaDesbloqueo(null);
            } else {
                int nuevosIntentos = usuario.getIntentosFallidos() + 1;
                usuario.setIntentosFallidos(nuevosIntentos);

                if (nuevosIntentos >= loginConfig.getMaxIntentosFallidos()) {
                    usuario.setBloqueado(true);
                    usuario.setFechaBloqueo(LocalDateTime.now());
                    usuario.setFechaDesbloqueo(LocalDateTime.now().plusMinutes(loginConfig.getMinutosBloqueo()));

                    System.out.println("USUARIO BLOQUEADO: " + username +
                            " - Intentos fallidos: " + nuevosIntentos +
                            " - Desbloqueo a las: " + usuario.getFechaDesbloqueo());
                }
            }
            usuarioRepository.save(usuario);
        }
    }

    /**
     * Verifica si un usuario está bloqueado por exceso de intentos fallidos.
     * Si el tiempo de bloqueo ha expirado, desbloquea automáticamente al usuario
     * y reinicia sus contadores de seguridad.
     *
     * @param username Nombre de usuario a verificar
     * @return true si el usuario está actualmente bloqueado, false en caso contrario
     */
    @Override
    public boolean usuarioEstaBloqueado(String username) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuario.isBloqueado() && usuario.getFechaDesbloqueo() != null) {
                if (LocalDateTime.now().isAfter(usuario.getFechaDesbloqueo())) {
                    usuario.setBloqueado(false);
                    usuario.setIntentosFallidos(0);
                    usuario.setFechaBloqueo(null);
                    usuario.setFechaDesbloqueo(null);
                    usuarioRepository.save(usuario);

                    System.out.println("USUARIO DESBLOQUEADO AUTOMÁTICAMENTE: " + username);
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Reinicia completamente los contadores de intentos fallidos y elimina
     * cualquier bloqueo activo para un usuario específico.
     * Útil después de un login exitoso o para desbloquear manualmente una cuenta.
     *
     * @param username Nombre de usuario al que se le reiniciarán los intentos fallidos
     */
    @Override
    public void reiniciarIntentosFallidos(String username) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setIntentosFallidos(0);
            usuario.setBloqueado(false);
            usuario.setFechaBloqueo(null);
            usuario.setFechaDesbloqueo(null);
            usuarioRepository.save(usuario);
        }
    }
}