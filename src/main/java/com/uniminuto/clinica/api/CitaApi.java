package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/clinica/v1/api/citas")
public interface CitaApi {

    /**
     * Crea una nueva cita relacionada con paciente y médico.
     *
     * @param cita objeto de la cita
     * @return la cita creada
     */
    @PostMapping(value = "/crear", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<Cita> crearCita(@RequestBody Cita cita);
}


