/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecetaService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
public class RecetaApiController implements RecetaApi{
    @Autowired
    private RecetaService recetaService;

    @Override
    public ResponseEntity<List<Receta>> listarReceta() {
        return ResponseEntity.ok(recetaService.encontrarTodasLasRecetas());
    }
    
    @Override
    public ResponseEntity<Receta> buscarRecetaXId(int id)
            throws BadRequestException {
        return ResponseEntity.ok(recetaService
                .encontrarRecetaPorId(id));
    }
    @Override
    public ResponseEntity<RespuestaRs> guardarRecetaPorIndicaciones(RecetaRq recetaRq)
            throws BadRequestException {
        return ResponseEntity.ok(this.recetaService.guardarRecetaPorIndicaciones(recetaRq));
    }
}