/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.service.RecetaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oskr
 */
@RestController
public class RecetaApiController implements RecetaApi{
    
    @Autowired
    private RecetaService recetaservice;
    @Override
    public ResponseEntity<Receta> creacionReceta(Receta receta){
        return ResponseEntity.ok(recetaservice.creacionReceta(receta));
    }
    
    @Override
    public ResponseEntity<List<Receta>> listadeReceta() {
        List<Receta> recetas = recetaservice.listadeReceta();
        return ResponseEntity.ok(recetas);
    }
}
