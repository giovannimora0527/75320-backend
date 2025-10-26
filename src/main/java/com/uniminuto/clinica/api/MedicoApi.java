package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



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

    /**
     * Guarda un medico en la bd.
     * @param medicoRq medico de entrada.
     * @return respuesta del servicio.
     * @throws BadRequestException excepcion.
     */
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarMedico(
            @RequestBody MedicoRq medicoRq)
            throws BadRequestException;

    /**
     * Actualiza un medico en la bd.
     * @param medicoRq medico de entrada.
     * @return respuesta del servicio.
     * @throws BadRequestException excepcion.
     */
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> actualizarMedico(
            @RequestBody MedicoRq medicoRq)
            throws BadRequestException;
}
