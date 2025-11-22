package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/auth")
public interface AutenticarApi {
    
    @PostMapping("/login")
    ResponseEntity<AutenticatorRs> autenticar(
            @Valid @RequestBody AuthenticatorRq request,
            HttpServletRequest httpRequest) throws BadRequestException;
    
    @PostMapping("/recuperar-contrasena")
    ResponseEntity<RespuestaRs> recuperarContrasena(
            @Valid @RequestBody RecuperarPasswordRq request,
            HttpServletRequest httpRequest) throws BadRequestException, MessagingException;
}