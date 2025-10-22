/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Oskr
 */
@RequestMapping("/receta")
public interface RecetaApi {
    
    @PostMapping("Creacion_de_Receta")
    ResponseEntity<Receta>creacionReceta(@RequestBody Receta receta);
    
        
    @GetMapping("/listaReceta")
    ResponseEntity<List<Receta>> listadeReceta();

    @PutMapping("{id}")
    ResponseEntity<Receta> actualizarReceta(@PathVariable Long id, @RequestBody Receta receta);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarReceta(@PathVariable Long id);
    
}
