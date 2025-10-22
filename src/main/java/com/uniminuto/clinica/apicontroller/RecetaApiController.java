package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.service.RecetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
@RequestMapping("/receta")
public class RecetaApiController {

    private final RecetaService recetaService;

    public RecetaApiController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping("/Creacion_de_Receta")
    public ResponseEntity<Receta> creacionReceta(@RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.creacionReceta(receta));
    }

    @GetMapping("/listaReceta")
    public ResponseEntity<List<Receta>> listadeReceta() {
        return ResponseEntity.ok(recetaService.listadeReceta());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizarReceta(@PathVariable Long id, @RequestBody Receta receta) {
        try {
            Receta actualizada = recetaService.actualizarReceta(id, receta);
            return ResponseEntity.ok(actualizada);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar la receta", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }
}
