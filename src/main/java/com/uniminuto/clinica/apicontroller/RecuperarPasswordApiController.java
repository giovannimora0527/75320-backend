package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecuperarPasswordApi;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecuperarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecuperarPasswordApiController implements RecuperarPasswordApi {

    @Autowired
    private RecuperarService recuperarService;

    @Override
    public ResponseEntity<RespuestaRs> recuperarPassword(RecuperarPasswordRq request) {
        return ResponseEntity.ok(recuperarService.recuperarPassword(request));
    }
}