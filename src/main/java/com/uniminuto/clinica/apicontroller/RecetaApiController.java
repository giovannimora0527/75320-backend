package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.service.RecetaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecetaApiController implements RecetaApi {

    @Autowired
    private RecetaService recetaService;

    @Override
    public ResponseEntity<Receta> crear(Receta receta) {
        try {
            Receta creada = recetaService.crear(receta);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Receta>> listar() {
        return ResponseEntity.ok(recetaService.listarRecetas());
    }
}










