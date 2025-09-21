package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Api de paciente
 */
/**
 * @author Anderson
 */

@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public interface PacienteApi {
    /**
     * Endpoint para listar los pacientes
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientes();
    /**
     * Endpoint para buscar los pacientes por documento
     */
    @RequestMapping(value = "/buscar-paciente-documento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacienteXIdentificacion(
            @RequestParam String numeroDocumento) 
            throws BadRequestException;
    /**
     * Endpoint para listar los pacientes de mayor a menor por la fecha de nacimiento
     */
    @RequestMapping(value ="/listar-mayor-a-menor",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientesPorFechaNacimiento();
}