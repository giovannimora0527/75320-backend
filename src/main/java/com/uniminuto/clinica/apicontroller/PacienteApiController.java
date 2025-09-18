/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alkri
 */

@RestController
public class PacienteApiController implements PacienteApi {
    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.encontrarTodosLosPacientes());
    }

    @Override
    public ResponseEntity<Paciente> buscarPacientePornombres(String nombres) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.encontrarPacientePorNombre(nombres));
    }
    
    @Override
    public ResponseEntity<Paciente> buscarPacientePorDocumento(String numeroDocumento) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.buscarPorNumeroDocumento(numeroDocumento));
    }
    
    /**
     * se hace uso del api controller para que los atributos sean correctos
     * @return
     * @throws BadRequestException 
     */
    @Override
    public ResponseEntity<List<Paciente>> listarPorFecha() throws BadRequestException {
      List<Paciente> pacientes = pacienteService.listarPorFecha();
     return ResponseEntity.ok(pacientes);
    }
}
