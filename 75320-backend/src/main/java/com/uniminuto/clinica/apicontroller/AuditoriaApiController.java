package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AuditoriaApi;
import com.uniminuto.clinica.model.AuditoriaLoginRq;
import com.uniminuto.clinica.model.AuditoriaLoginRs;
import com.uniminuto.clinica.service.AuditoriaLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la consulta de logs de auditoría.
 * 
 * @author Sistema
 */
@RestController
public class AuditoriaApiController implements AuditoriaApi {
    
    private final AuditoriaLoginService auditoriaLoginService;
    
    public AuditoriaApiController(AuditoriaLoginService auditoriaLoginService) {
        this.auditoriaLoginService = auditoriaLoginService;
    }
    
    @Override
    public ResponseEntity<AuditoriaLoginRs> consultarAuditoria(AuditoriaLoginRq request) {
        AuditoriaLoginRs respuesta = auditoriaLoginService.consultarAuditoria(request);
        return ResponseEntity.ok(respuesta);
    }
}

