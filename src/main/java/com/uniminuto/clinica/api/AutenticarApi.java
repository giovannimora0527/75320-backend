package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import jakarta.validation.Valid;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AutenticarApi {
    @PostMapping("/login")
    ResponseEntity<AutenticatorRs> autenticar(@Valid @RequestBody AuthenticatorRq request) throws BadRequestException;
}
