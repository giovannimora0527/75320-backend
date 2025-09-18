package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clinica/v1/api/recetas")
public interface RecetaApi {

    /**
     * Crea una nueva receta asociada a una cita.
     *
     * @param receta objeto de la receta
     * @return la receta creada
     */
    @PostMapping(value = "/crear", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<Receta> crearReceta(@RequestBody Receta receta);

    /**
     * Lista todas las recetas.
     *
     * @return lista de recetas
     */
    @GetMapping(value = "/listar", produces = {"application/json"})
    ResponseEntity<List<Receta>> listarRecetas();

    /**
     * Obtiene una receta por su ID.
     *
     * @param id ID de la receta
     * @return la receta encontrada
     */
    @GetMapping(value = "/{id}", produces = {"application/json"})
    ResponseEntity<Receta> obtenerRecetaPorId(@PathVariable Long id);
}

