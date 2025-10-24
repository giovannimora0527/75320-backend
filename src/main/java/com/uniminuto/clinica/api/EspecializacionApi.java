package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RequestMapping("/especializacion")
public interface EspecializacionApi {

    /**
     * Listar especializaciones
     * @return List<Especializacion> lista.
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Especializacion>> listarEspecializaciones();
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarEspecializacion(
            @RequestBody EspecializacionRq especializacionRq
    ) throws BadRequestException;
    
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> actualizarEspecializacion(
            @RequestBody EspecializacionRq especializacionRq
    ) throws BadRequestException;
}