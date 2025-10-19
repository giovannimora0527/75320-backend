package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;

import java.util.List;

import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lmora
 */
@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public interface PacienteApi {

    /**
     * Lista los usuarios de la bd.
     *
     * @return
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientes();


    @RequestMapping(value = "/buscar-paciente-documento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacienteXIdentificacion(
            @RequestParam String numeroDocumento)
            throws BadRequestException;

    @RequestMapping(value = "/listar-fecha-nacimiento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientesPorEdad();

    @RequestMapping(value = "/agregar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> agregarPaciente(
            @RequestBody PacienteRq pacienteRq)
            throws BadRequestException;

}