/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 *
 * @author Oskr
 */
@RestController
public class CitaApiController implements CitaApi {

    @Autowired
    private CitaService citaService;

    @Override
    public ResponseEntity<Cita> creacionCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.guardadoCita(cita));
    }

    @Override
    public ResponseEntity<List<Cita>> listadoCitas() {
        return ResponseEntity.ok(citaService.listadodeCitasRecientes());
    }
}