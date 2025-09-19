package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.service.CitaService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitaApiController implements CitaApi {

    @Autowired
    private CitaService citaService;

    @Override
    public ResponseEntity<Cita> crear(Cita cita) {
        try {
            Cita creada = citaService.guardarCita(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<List<Cita>> listarRecientes() {
        return ResponseEntity.ok(citaService.listarCitasRecientes());
    }

    @Override
    public ResponseEntity<List<Cita>> listarPorRango(@RequestParam("desde") LocalDateTime desde,
            @RequestParam("hasta") LocalDateTime hasta) {
        return ResponseEntity.ok(citaService.listarCitasPorRango(desde, hasta));
    }
}










