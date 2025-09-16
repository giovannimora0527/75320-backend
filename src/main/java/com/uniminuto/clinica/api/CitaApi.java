package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/cita")
public interface CitaApi {

    @RequestMapping(
            value = "/agendarCita",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Cita> crearCita(@RequestBody Cita cita);
}