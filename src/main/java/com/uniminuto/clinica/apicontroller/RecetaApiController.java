package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecetaService;
import jakarta.validation.Valid;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecetaApiController implements RecetaApi {

    private final RecetaService recetaService;

    public RecetaApiController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarReceta(@Valid @RequestBody RecetaRq recetaRq) {
        try {
            RespuestaRs respuesta = recetaService.guardarReceta(recetaRq);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMessage("Error al crear la receta: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Override
    public ResponseEntity<List<Receta>> listarRecetas() {
        try {
            List<Receta> recetas = recetaService.listarRecetas();
            return ResponseEntity.ok(recetas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<RespuestaRs> eliminarReceta(@RequestParam Integer id) throws BadRequestException {
        try {
            RespuestaRs respuesta = recetaService.eliminarReceta(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMessage("Error al eliminar la receta: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Override
    public ResponseEntity<RespuestaRs> actualizarReceta(@RequestParam Integer id, @RequestBody RecetaRq recetaRq){
        try {
            RespuestaRs respuesta = recetaService.actualizarReceta(id, recetaRq);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMessage("Error al actualizar la receta: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
