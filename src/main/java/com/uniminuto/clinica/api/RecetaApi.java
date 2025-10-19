/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */

@CrossOrigin(origins = "*")
@RequestMapping("/receta")
public interface RecetaApi {
    
    @PostMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<RespuestaRs> guardarReceta(@RequestBody RecetaRq recetaRq);
    
    @GetMapping(value = "/listar", produces = {"application/json"})
    ResponseEntity<List<Receta>> listarRecetas();
    
}
