/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CitaApiController implements CitaApi {

    @Autowired
    private CitaService citaService;

    @Override
    public ResponseEntity<Cita> crearCita(Cita cita) {
        try {
            Cita nuevaCita = citaService.crearCita(cita);
            return ResponseEntity.ok(nuevaCita);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Cita>> listarCitasOrdenadas() {
        try {
            List<Cita> citas = citaService.getAllCitasOrdenadas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<Cita>> buscarCitasPorPaciente(Long pacienteId) {
        try {
            List<Cita> citas = citaService.getCitasPorPaciente(pacienteId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<Cita>> buscarCitasPorMedico(Long medicoId) {
        try {
            List<Cita> citas = citaService.getCitasPorMedico(medicoId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<Cita>> buscarCitasPorEstado(String estado) {
        // Implementar este método en el servicio
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Cita> actualizarCita(Long id, Cita cita) {
        // Implementar este método en el servicio
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Cita> cancelarCita(Long id) {
        // Implementar este método en el servicio
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Cita> confirmarCita(Long id) {
        // Implementar este método en el servicio
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> eliminarCita(Long id) {
        // Implementar este método en el servicio
        return ResponseEntity.notFound().build();
    }
}