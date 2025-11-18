package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.PasswordRecoveryRequest;

public interface PasswordRecoveryService {
    void recuperarContrasena(PasswordRecoveryRequest request, String ipOrigen);
}
