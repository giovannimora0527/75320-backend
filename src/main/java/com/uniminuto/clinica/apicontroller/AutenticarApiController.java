package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AutenticarApi;
import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.PasswordRecoveryRequest;
import com.uniminuto.clinica.model.PasswordRecoveryResponse;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.PasswordRecoveryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AutenticarApiController implements AutenticarApi {

    @Autowired
    private AutenticarService autenticarService;

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @Override
    public ResponseEntity<AutenticatorRs> autenticar(AuthenticatorRq request) throws BadRequestException {
        return ResponseEntity.ok(this.autenticarService.autenticar(request));
    }

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<PasswordRecoveryResponse> recuperarContrasena(
            @RequestBody PasswordRecoveryRequest request,
            HttpServletRequest httpRequest) {

        String ip = httpRequest.getRemoteAddr();

        // Lógica en el service (auditoría, generación de clave, envío de correo)
        passwordRecoveryService.recuperarContrasena(request, ip);

        // Mensaje genérico (no dice si el usuario existe o no)
        return ResponseEntity.ok(new PasswordRecoveryResponse(
                "Si el usuario existe, se enviará una contraseña temporal al correo registrado."
        ));
    }
}
