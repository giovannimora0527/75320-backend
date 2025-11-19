package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.LoginIntento;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.repository.LoginIntentoRepository;
import com.uniminuto.clinica.service.LoginIntentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación del servicio de gestión de intentos de login
 * @author Andre
 */
@Slf4j
@Service
public class LoginIntentoServiceImpl implements LoginIntentoService {
    
    @Autowired
    private LoginIntentoRepository loginIntentoRepository;
    
    @Override
    public void registrarIntento(Long usuarioId, String ip, boolean exito) {
        LoginIntento intento = new LoginIntento();
        
        // Crear usuario con solo el ID
        Usuario u = new Usuario();
        u.setId(usuarioId);
        
        intento.setUsuario(u);
        intento.setFechaHora(LocalDateTime.now());
        intento.setIp(ip);
        intento.setExito(exito);
        
        loginIntentoRepository.save(intento);
        
        log.info("Intento de login registrado - Usuario ID: {}, Éxito: {}, IP: {}", 
                usuarioId, exito, ip);
    }
    
    @Override
    public void registrarIntento(Usuario usuario, String ip, boolean exito) {
        LoginIntento intento = new LoginIntento();
        
        intento.setUsuario(usuario);
        intento.setFechaHora(LocalDateTime.now());
        intento.setIp(ip);
        intento.setExito(exito);
        
        loginIntentoRepository.save(intento);
        
        log.info("Intento de login registrado - Usuario: {}, Éxito: {}, IP: {}", 
                usuario.getUsername(), exito, ip);
    }
    
    @Override
    public List<LoginIntento> obtenerIntentosPorUsuario(Long usuarioId) {
        return loginIntentoRepository.findByUsuarioId(usuarioId);
    }
}