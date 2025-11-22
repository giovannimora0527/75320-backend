package com.uniminuto.clinica.service;

public interface AuditoriaLoginService {
    void registrarIntentoLogin(String username, String ipAddress, boolean exitoso, String descripcion);
    boolean usuarioEstaBloqueado(String username);
    void reiniciarIntentosFallidos(String username);
}