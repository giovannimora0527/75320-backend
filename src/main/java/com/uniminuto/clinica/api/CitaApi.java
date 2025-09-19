/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Oskr
 */
@RequestMapping("/cita")
public interface CitaApi {
    
    @PostMapping("/creacionCita")
    ResponseEntity<Cita> creacionCita(@RequestBody Cita cita);
    @GetMapping("/listadoCita")
    ResponseEntity<List<Cita>> listadoCitas();
    
}
