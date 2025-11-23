package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.mail.MessagingException;

@CrossOrigin(origins = "*")
@RequestMapping("/email")
public interface EmailApi {

    @GetMapping("/test")
    ResponseEntity<RespuestaRs> testEmail() throws BadRequestException, MessagingException;
    
    /**
     * Endpoint para enviar un correo de prueba a un destinatario específico.
     * @param correo Correo electrónico del destinatario.
     * @return Respuesta con el resultado del envío.
     * @throws BadRequestException Si hay un error en la solicitud.
     * @throws MessagingException Si hay un error al enviar el correo.
     */
    @GetMapping("/test/{correo}")
    ResponseEntity<RespuestaRs> testEmailTo(@PathVariable String correo) 
            throws BadRequestException, MessagingException;
}
