package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Medico;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lmora
 */
@CrossOrigin(origins = "*")
@RequestMapping("/medico")
public interface MedicoApi {
    /**
     * Lista los usuarios de la bd.
     *
     * @return
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Medico>> listarMedicos();
    
    
    /**
     * Lista los medicos por especializacion de la bd.
     *
     * @return
     */
    @RequestMapping(value = "/listar-por-especializacion",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Medico>> listarMedicosPorEspecialidad(
       @RequestParam String codigo
    )  throws BadRequestException;
}
