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
 * ApiRest para logica de paciente
 * *
 * @author nicolas
 */
@CrossOrigin(origins = "*")
@RequestMapping("/Paciente")
public interface PacienteApi {
    /**
     * Lista los usuarios de la bd Paciente
     * @return 
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPaciente();
    
    /**
     *Trae el usuario solicitado apartir de un numero de documento
     * 
     * @return
     */
    @RequestMapping(value = "/buscar-paciente",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacienteXNumero_documento(
            @RequestParam String numero_documento)
            throws BadRequestException;
    @RequestMapping(value = "/listarPorFechaNacimiento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientesPorfechaNacimiento();
}
