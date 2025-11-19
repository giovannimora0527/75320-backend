package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import org.apache.coyote.BadRequestException;

/**
 * Servicio de autenticación de usuarios
 */
public interface AutenticarService {
    
    /**
     * Autentica un usuario validando credenciales, intentos fallidos y bloqueos
     * @param request Credenciales del usuario (username y password)
     * @param ip Dirección IP desde donde se realiza el intento de login
     * @return Respuesta con token JWT si la autenticación es exitosa
     * @throws BadRequestException Si las credenciales son inválidas o la cuenta está bloqueada
     */
    AutenticatorRs autenticar(AuthenticatorRq request, String ip) throws BadRequestException;
}