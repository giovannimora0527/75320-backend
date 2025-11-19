package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.LoginIntento;
import com.uniminuto.clinica.entity.Usuario;

import java.util.List;

/**
 * Servicio para gestión de intentos de login
 * @author Andre
 */
public interface LoginIntentoService {
    
    /**
     * Registra un intento de login (exitoso o fallido)
     * @param usuarioId ID del usuario
     * @param ip Dirección IP desde donde se intentó el login
     * @param exito Indica si el intento fue exitoso
     */
    void registrarIntento(Long usuarioId, String ip, boolean exito);
    
    /**
     * NUEVO: Registra un intento de login usando el objeto Usuario completo
     * @param usuario Objeto Usuario completo
     * @param ip Dirección IP desde donde se intentó el login
     * @param exito Indica si el intento fue exitoso
     */
    void registrarIntento(Usuario usuario, String ip, boolean exito);
    
    /**
     * Obtiene la lista de intentos para un usuario
     * @param usuarioId ID del usuario
     * @return Lista de intentos de login
     */
    List<LoginIntento> obtenerIntentosPorUsuario(Long usuarioId);
}