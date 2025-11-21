package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.EspecializacionRq;
import java.util.List;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API de Especialización
 * Define los endpoints disponibles para la gestión de especializaciones.
 * 
 * @author 
 */
@CrossOrigin(origins = "*")
@RequestMapping("/especializacion")
public interface EspecializacionApi {
    
    @RequestMapping(
        value = "/listar",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Especializacion>> listarEspecializaciones();

    @RequestMapping(
        value = "/buscar-por-codigo",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<Especializacion> buscarPorCodigo(@RequestParam String codigo) throws BadRequestException;

    @RequestMapping(
        value = "/guardar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> guardarEspecializacion(@RequestBody EspecializacionRq especializacionRq) throws BadRequestException;

    @RequestMapping(
        value = "/eliminar",
        produces = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> eliminarEspecializacion(@RequestParam Integer id) throws BadRequestException;

    @RequestMapping(
        value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> actualizarEspecializacion(
        @RequestParam Integer id,
        @RequestBody EspecializacionRq especializacionRq
    ) throws BadRequestException;
}
