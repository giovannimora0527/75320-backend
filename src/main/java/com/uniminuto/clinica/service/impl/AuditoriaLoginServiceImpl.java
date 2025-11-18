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