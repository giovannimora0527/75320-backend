package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.PacienteRq;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarPaciente(
            @RequestBody PacienteRq pacienteRq)
            throws BadRequestException;

    @RequestMapping(value = "/eliminar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> eliminarPaciente(
            @RequestParam Integer id)
            throws BadRequestException;
    
    @RequestMapping(value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> actualizarPaciente(
        @RequestParam Integer id,
        @RequestBody PacienteRq pacienteRq)
        throws BadRequestException;
}


