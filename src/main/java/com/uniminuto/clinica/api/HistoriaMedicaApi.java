package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.HistoriaMedica;
import com.uniminuto.clinica.model.HistoriaMedicaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API para la gestión de historias médicas.
 * 
 * Endpoints:
 *  - GET /historia-medica/listar → Lista todas las historias médicas.
 *  - GET /historia-medica/buscar/{id} → Obtiene una historia médica por ID.
 *  - GET /historia-medica/paciente/{pacienteId} → Lista historias de un paciente.
 *  - POST /historia-medica/guardar → Crea una nueva historia médica.
 *  - POST /historia-medica/actualizar → Actualiza una historia médica existente.
 *  - POST /historia-medica/eliminar → Elimina una historia médica por su ID.
 */
@CrossOrigin(origins = "*")
@RequestMapping("/historia-medica")
public interface HistoriaMedicaApi {

    @GetMapping(
        value = "/listar",
        produces = "application/json"
    )
    ResponseEntity<List<HistoriaMedica>> listarHistorias();

    @GetMapping(
        value = "/buscar/{id}",
        produces = "application/json"
    )
    ResponseEntity<HistoriaMedica> obtenerHistoria(@PathVariable Integer id) throws BadRequestException;

    @GetMapping(
        value = "/paciente/{pacienteId}",
        produces = "application/json"
    )
    ResponseEntity<List<HistoriaMedica>> listarPorPaciente(@PathVariable Integer pacienteId);

    @PostMapping(
        value = "/guardar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> guardarHistoria(
        @Valid @RequestBody HistoriaMedicaRq historiaMedicaRq
    ) throws BadRequestException;

    @PostMapping(
        value = "/actualizar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> actualizarHistoria(
        @RequestParam Integer id,
        @Valid @RequestBody HistoriaMedicaRq historiaMedicaRq
    ) throws BadRequestException;

    @PostMapping(
        value = "/eliminar",
        produces = "application/json"
    )
    ResponseEntity<RespuestaRs> eliminarHistoria(
        @RequestParam Integer id
    ) throws BadRequestException;
}

