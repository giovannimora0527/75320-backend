package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import jakarta.validation.Valid;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public interface AutenticarApi {
    @PostMapping("/login")
    ResponseEntity<AutenticatorRs> autenticar(@Valid @RequestBody AuthenticatorRq request) throws BadRequestException;
    
    /**
     * Endpoint para recuperar contraseña.
     * El usuario ingresa su nombre de usuario y se envía una contraseña temporal al correo registrado.
     * 
     * @param request Solicitud con el nombre de usuario.
     * @return Respuesta genérica (por seguridad, no revela si el usuario existe).
     * @throws BadRequestException Si hay un error en la solicitud.
     */
    @PostMapping("/recuperar-contrasena")
    ResponseEntity<RespuestaRs> recuperarPassword(@Valid @RequestBody RecuperarPasswordRq request) throws BadRequestException;
}
