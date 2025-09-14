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
<<<<<<< HEAD
 * @author Andre
 */

@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public interface PacienteApi {
=======
 * @author lmora
 */
@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public interface PacienteApi {
    
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
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
    
<<<<<<< HEAD
    //**  segundo servicio  **//
    @RequestMapping(value = "/buscar-por-documento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPorDocumento(
            @RequestParam String numeroDocumento) 
            throws BadRequestException;    
}
=======
    
        @RequestMapping(value = "/buscar-paciente-documento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacienteXIdentificacion(
            @RequestParam String numeroDocumento) 
            throws BadRequestException;
}


>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
