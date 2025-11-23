package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.EmailApi;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.EmailService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;

@RestController
public class EmailApiController implements EmailApi {

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<RespuestaRs> testEmail() throws BadRequestException, MessagingException {
        return ResponseEntity.ok(emailService.testEmail("giovannimora0527@gmail.com"));
    }
    
    @Override
    public ResponseEntity<RespuestaRs> testEmailTo(String correo) throws BadRequestException, MessagingException {
        // Validar formato de correo básico
        if (correo == null || correo.trim().isEmpty() || !correo.contains("@")) {
            throw new BadRequestException("Correo electrónico inválido");
        }
        return ResponseEntity.ok(emailService.testEmail(correo.trim()));
    }
}
