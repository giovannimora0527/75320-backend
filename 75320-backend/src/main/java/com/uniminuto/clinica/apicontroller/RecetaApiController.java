package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecetaService;
import jakarta.validation.Valid;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para la gestión de recetas médicas.
 * Implementa la interfaz RecetaApi.
 *
 * Endpoints:
 *  - POST /api/recetas/guardar → Crea una receta
 *  - GET  /api/recetas/listar → Lista todas las recetas
 *  - POST /api/recetas/eliminar?id={id} → Elimina una receta
 *  - POST /api/recetas/actualizar?id={id} → Actualiza una receta
 * 
 * @author 
 */
@RestController
public class RecetaApiController implements RecetaApi {

    private final RecetaService recetaService;

    // ✅ Inyección por constructor (mejor práctica)
    public RecetaApiController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    /**
     * Guarda una nueva receta médica.
     */
    @Override
    public ResponseEntity<RespuestaRs> guardarReceta(@Valid @RequestBody RecetaRq recetaRq)
            throws BadRequestException {
        try {
            return ResponseEntity.ok(recetaService.guardarReceta(recetaRq));
        } catch (Exception e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje("Error al guardar la receta: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Lista todas las recetas disponibles.
     */
    @Override
    public ResponseEntity<List<Receta>> listarRecetas() {
        try {
            return ResponseEntity.ok(recetaService.listarRecetas());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Elimina una receta por su ID.
     */
    @Override
    public ResponseEntity<RespuestaRs> eliminarReceta(@RequestParam Integer id)
            throws BadRequestException {
        try {
            return ResponseEntity.ok(recetaService.eliminarReceta(id));
        } catch (Exception e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje("Error al eliminar la receta: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Actualiza una receta existente.
     */
    @Override
    public ResponseEntity<RespuestaRs> actualizarReceta(
            @RequestParam Integer id,
            @RequestBody RecetaRq recetaRq) {
        try {
            return ResponseEntity.ok(recetaService.actualizarReceta(id, recetaRq));
        } catch (Exception e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje("Error al actualizar la receta: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
