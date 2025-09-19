/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * API REST para la gestión de recetas médicas.
 * Proporciona servicios para crear, listar y gestionar recetas médicas.
 *
 * @author anago
 */
@CrossOrigin(origins = "*")
@RequestMapping("/api/recetas")
public interface RecetaApi {

    /**
     * Crea una nueva receta médica.
     *
     * @param receta Objeto Receta a crear
     * @return Receta creada con estado HTTP 200
     */
    @RequestMapping(value = "/crear",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Receta> crearReceta(@RequestBody Receta receta);

    /**
     * Lista todas las recetas ordenadas por fecha de creación descendente.
     * Las recetas más recientes aparecen primero.
     *
     * @return Lista de recetas ordenadas por fecha de creación
     */
    @RequestMapping(value = "/listar-ordenadas",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Receta>> listarRecetasOrdenadas();

    /**
     * Busca recetas por ID de cita.
     *
     * @param citaId ID de la cita
     * @return Lista de recetas de la cita
     */
    @RequestMapping(value = "/buscar-por-cita/{citaId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Receta>> buscarRecetasPorCita(@PathVariable("citaId") Long citaId);

    /**
     * Busca una receta por su ID.
     *
     * @param id ID de la receta
     * @return Receta encontrada
     */
    @RequestMapping(value = "/buscar/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Receta> buscarRecetaPorId(@PathVariable("id") Long id);

    /**
     * Actualiza una receta existente.
     *
     * @param id ID de la receta a actualizar
     * @param receta Receta con los datos actualizados
     * @return Receta actualizada
     */
    @RequestMapping(value = "/actualizar/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Receta> actualizarReceta(@PathVariable("id") Long id, @RequestBody Receta receta);

    /**
     * Elimina una receta por su ID.
     *
     * @param id ID de la receta a eliminar
     * @return Respuesta de confirmación
     */
    @RequestMapping(value = "/eliminar/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> eliminarReceta(@PathVariable("id") Long id);
}