package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.RecuperacionPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import javax.mail.MessagingException;

/**
 *
 * @author Andre
 */
public interface RecuperacionPasswordService {
    /**
     * Procesa la solicitud de recuperación de contraseña.
     * Genera contraseña temporal y la envía por email si el usuario existe.
     * Registra auditoría de todos los intentos (exitosos y fallidos).
     * 
     * @param request Solicitud con el nombre de usuario
     * @param ipOrigen IP desde donde se realiza la solicitud
     * @return Respuesta genérica que no revela si el usuario existe
     * @throws MessagingException Si hay un error al enviar el correo
     */
    RespuestaRs recuperarPassword(RecuperacionPasswordRq request, String ipOrigen) 
            throws MessagingException;
    
}
