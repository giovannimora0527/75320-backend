package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperacionPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import javax.mail.MessagingException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/auth")
public interface AutenticarApi {
    
    @PostMapping("/login")
    ResponseEntity<AutenticatorRs> autenticar(
        @Valid @RequestBody AuthenticatorRq request,
        HttpServletRequest httpRequest
    ) throws BadRequestException;
    
        /**
     * Endpoint para recuperación de contraseña.
     * Envía una contraseña temporal al correo del usuario si existe.
     * Por seguridad, siempre devuelve el mismo mensaje genérico.
     * 
     * @param request Username del usuario que solicita recuperación
     * @param httpRequest Solicitud HTTP para obtener IP
     * @return Respuesta genérica sin revelar si el usuario existe
     * @throws MessagingException Si hay error al enviar el correo
     */
      
    @PostMapping("/recuperar-contrasena")
    ResponseEntity<RespuestaRs> recuperarPassword(
        @Valid @RequestBody RecuperacionPasswordRq request,
        HttpServletRequest httpRequest  
    ) throws MessagingException; 
}
