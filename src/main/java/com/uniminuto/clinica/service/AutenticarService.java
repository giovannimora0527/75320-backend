package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;

import javax.mail.MessagingException;

public interface AutenticarService {

    /**
     * Autentica un usuario.
     * @param request Datos de autenticación (username + password).
     * @param ip Dirección IP del cliente.
     * @return Token de autenticación.
     * @throws BadRequestException Si hay error en la autenticación.
     */
    AutenticatorRs autenticar(AuthenticatorRq request, String ip) throws BadRequestException;

    /**
     * Inicia proceso de recuperación de contraseña usando el CORREO.
     * @param request Correo del usuario.
     * @param ip Dirección IP del cliente.
     * @return Respuesta genérica.
     * @throws BadRequestException Si hay error.
     * @throws MessagingException Si hay error al enviar el correo.
     */
    RespuestaRs recuperarContrasena(RecuperarPasswordRq request, String ip)
            throws BadRequestException;
}
