package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import jakarta.validation.Valid;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * API para la gestión de recetas médicas.
 * 
 * Endpoints:
 *  - GET /receta/listar → Lista todas las recetas.
 *  - POST /receta/guardar → Crea una nueva receta.
 *  - POST /receta/actualizar → Actualiza una receta existente.
 *  - POST /receta/eliminar → Elimina una receta por su ID.
 * 
 * @author 
 */
@CrossOrigin(origins = "*")
@RequestMapping("/receta")
public interface RecetaApi {

    /**
     * Lista todas las recetas registradas en el sistema.
     */
    @GetMapping(
        value = "/listar",
        produces = "application/json"
    )
    ResponseEntity<List<Receta>> listarRecetas();

    /**
     * Guarda una nueva receta.
     */
    @PostMapping(
        value = "/guardar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> guardarReceta(
        @Valid @RequestBody RecetaRq recetaRq
    ) throws BadRequestException;

    /**
     * Actualiza una receta existente.
     */
    @PostMapping(
        value = "/actualizar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> actualizarReceta(
        @RequestParam Integer id,
        @Valid @RequestBody RecetaRq recetaRq
    ) throws BadRequestException;

    /**
     * Elimina una receta por su ID.
     */
    @PostMapping(
        value = "/eliminar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> eliminarReceta(
        @RequestParam Integer id
    ) throws BadRequestException;
}
