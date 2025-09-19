package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*")
@RequestMapping("/api/recetas")
public interface RecetaApi {

    @RequestMapping(value = "/crear",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Receta> crear(@RequestBody Receta receta);

    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Receta>> listar();
}










