package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/receta")
public interface RecetaApi {

    @RequestMapping(
            value = "/{citaId}/recetas",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> createReceta(
            @PathVariable Long citaId,  
            @RequestBody Receta receta)
            throws BadRequestException;

    @RequestMapping(
            value = "/{citaId}/recetas",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Receta>> getRecetasByCita(
            @PathVariable Long citaId) 
            throws BadRequestException;

    @RequestMapping(
            value = "/recetas/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Receta> getRecetaById(
            @PathVariable Long id)      
            throws BadRequestException;
}



