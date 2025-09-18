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
 *
 * @author Alkri
 */

@CrossOrigin(origins = "*")
@RequestMapping("/paciente")

public interface PacienteApi {
    
    /* Lista los pacientes de la bd */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientes();
    
    @RequestMapping(value = "/buscar-paciente",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacientePornombres(
            @RequestParam String nombres) 
            throws BadRequestException;
    
    @RequestMapping(value = "/buscar-documento",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacientePorDocumento(
        @RequestParam String numeroDocumento) 
        throws BadRequestException;

    /**
     * se ponen los datos los cuales se pondran en el postman para la visualizacion
     * @return
     * @throws BadRequestException 
     */
    @RequestMapping(value = "/cumpleaños",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.GET)
ResponseEntity<List<Paciente>> listarPorFecha() throws BadRequestException;
    
}
