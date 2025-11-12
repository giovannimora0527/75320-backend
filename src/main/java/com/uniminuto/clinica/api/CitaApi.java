package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import org.springframework.web.bind.annotation.*;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import jakarta.validation.Valid;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/cita")
public interface CitaApi {
    
    @RequestMapping(value = "/por-fechahora",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> listarCitaPorFechaHora();
    
    @RequestMapping(value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> actualizarCita(@RequestParam Integer id, @RequestBody CitaRq citaRq) throws BadRequestException;
    
    @RequestMapping(value = "/eliminar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> eliminarCita(@RequestParam Integer id) throws BadRequestException;
    
    /**
     * Api para listar todas las citas del sistema.
     * @return listado de citas.
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> listarCitas();

    /**
     * Api para guardar una cita nueva.
     * @param citaRq cita de entrada.
     * @return Respuesta del servicio.
     * @throws BadRequestException excepcion.
     */
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarCita(
            @RequestBody @Valid CitaRq citaRq
            ) throws BadRequestException;

    /**
     * Api para listar todas las citas del sistema.
     * @param pacienteIds
     * @return listado de citas.
     * @throws BadRequestException
     */
    @RequestMapping(value = "/listar-citas-paciente",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> listarCitasPorPaciente(
            @RequestParam Integer pacienteIds
    ) throws BadRequestException;
}
