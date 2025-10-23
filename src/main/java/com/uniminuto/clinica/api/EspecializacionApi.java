package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Especializacion;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * API REST para la gestión de especializaciones médicas
 * 
 * @author JulianLopez
 */
@CrossOrigin(origins = "*")
@RequestMapping("/especializacion")
public interface EspecializacionApi {
    
    /**
     * Lista todas las especializaciones médicas
     * 
     * @return Lista de especializaciones
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Especializacion>> listarEspecializaciones();
    
    /**
     * Busca una especialización por su ID
     * 
     * @param id ID de la especialización
     * @return Especialización encontrada
     * @throws BadRequestException si no se encuentra
     */
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Especializacion> buscarEspecializacionPorId(@PathVariable Integer id) 
            throws BadRequestException;
    
    /**
     * Crea una nueva especialización médica
     * 
     * @param especializacion Datos de la especialización a crear
     * @return Especialización creada
     * @throws BadRequestException si hay errores en los datos
     */
    @RequestMapping(value = "/crear",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Especializacion> crearEspecializacion(@RequestBody Especializacion especializacion) 
            throws BadRequestException;
    
    /**
     * Actualiza una especialización existente
     * 
     * @param id ID de la especialización a actualizar
     * @param especializacion Datos actualizados
     * @return Especialización actualizada
     * @throws BadRequestException si no se encuentra
     */
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Especializacion> actualizarEspecializacion(
            @PathVariable Integer id,
            @RequestBody Especializacion especializacion) 
            throws BadRequestException;
    
    /**
     * Elimina una especialización por su ID
     * 
     * @param id ID de la especialización a eliminar
     * @return Respuesta sin contenido
     * @throws BadRequestException si no se encuentra
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> eliminarEspecializacion(@PathVariable Integer id) 
            throws BadRequestException;
}