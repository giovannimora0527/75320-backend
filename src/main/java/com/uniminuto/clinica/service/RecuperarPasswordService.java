package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;
import jakarta.mail.MessagingException;

/**
 * Servicio para la recuperación de contraseña de usuarios.
 * Incluye funcionalidad de auditoría y envío de contraseñas temporales.
 */
public interface RecuperarPasswordService {
    
    /**
     * Procesa la solicitud de recuperación de contraseña.
     * 
     * @param request Solicitud con el nombre de usuario.
     * @param ipOrigen IP desde donde se realiza la solicitud (opcional).
     * @return Respuesta indicando que la solicitud fue procesada (sin revelar si el usuario existe).
     * @throws BadRequestException Si hay un error en el procesamiento.
     * @throws MessagingException Si hay un error al enviar el correo.
     */
    RespuestaRs recuperarPassword(RecuperarPasswordRq request, String ipOrigen) 
            throws BadRequestException, MessagingException;
}




