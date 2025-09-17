package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitaApiController implements CitaApi {

    private final CitaService citaService;

    // Constructor manual (reemplaza @RequiredArgsConstructor)
    public CitaApiController(CitaService citaService) {
        this.citaService = citaService;
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarCita(CitaRq citaRq) {
        try {
            return ResponseEntity.ok(citaService.guardarCita(citaRq));
        } catch (Exception e) {
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(400);
            respuesta.setMessage("Error al crear la cita");
            return ResponseEntity.badRequest().body(respuesta);
        }
    }
}