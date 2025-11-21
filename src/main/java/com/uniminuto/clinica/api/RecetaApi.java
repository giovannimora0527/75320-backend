package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api/recetas")
public interface RecetaApi {

    // ✅ Crear o guardar receta
    @PostMapping(
        value = "/guardar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> guardarReceta(@Valid @RequestBody RecetaRq recetaRq) throws BadRequestException;

    // ✅ Listar recetas
    @GetMapping(
        value = "/listar",
        produces = "application/json"
    )
    ResponseEntity<List<Receta>> listarRecetas();

    // ✅ Eliminar receta
    @PostMapping(
        value = "/eliminar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> eliminarReceta(@RequestParam Integer id) throws BadRequestException;

    // ✅ Actualizar receta
    @PostMapping(
        value = "/actualizar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> actualizarReceta(@RequestParam Integer id, @RequestBody RecetaRq recetaRq);
}
