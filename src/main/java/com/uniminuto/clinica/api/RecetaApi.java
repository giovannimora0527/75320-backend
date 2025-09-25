package com.uniminuto.clinica.api;

<<<<<<< HEAD
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/receta")
public interface RecetaApi {

    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Receta>> listarRecetas();


    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarReceta(
            @RequestBody @Valid RecetaRq recetaRq
    ) throws BadRequestException;
}
=======
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

>>>>>>> origin/Mariana_976621Castillo
