/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Api de Receta
 * @author DELL
 */

/**
 * Permite que Postman consuma la API
 */
@CrossOrigin(origins = "*")
/**
 * Todas las rutas de esta API comenzarán con /receta
 */
@RequestMapping("/receta")
public interface RecetaApi {
    /**
     * Endpoint para listar las recetas de la bd.
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"}, // La respuesta será en formato JSON
            consumes = {"application/json"}, // Indica que consume JSON
            method = RequestMethod.GET) // Tipo de petición: GET
    ResponseEntity<List<Receta>> listarReceta();
    
    /**
     * Endpoint para buscar una receta por su id.
     */
    @RequestMapping(value = "/buscar-receta",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Receta> buscarRecetaXId(
            @RequestParam int id) // Recibe el parámetro "id" desde la URL
            throws BadRequestException; //Puede lanzar una excepción si hay un error
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarRecetaPorIndicaciones(
            @RequestBody RecetaRq recetaRq
    ) throws BadRequestException;
}