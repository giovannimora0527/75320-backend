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
 * Api de medico
 */
/**
 * @author Anderson
 */

@CrossOrigin(origins = "*")
@RequestMapping("/medico")
public interface MedicoApi {
    /**
     * Endpoint para listar los medicos
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Medico>> listarMedicos();
    /**
     * Endpoint para listar los medicos por especializacion
     */
    @RequestMapping(value = "/listar-por-especializacion",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Medico>> listarMedicosPorEspecialidad(
       @RequestParam String codigo
    )  throws BadRequestException;
}
