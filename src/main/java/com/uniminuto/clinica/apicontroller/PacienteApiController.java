package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementación de PacienteApi.
 */
@RestController
public class PacienteApiController implements PacienteApi {

    private final PacienteService pacienteService;

    public PacienteApiController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Override
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @Override
    public ResponseEntity<Paciente> buscarPorDocumento(String numeroDocumento) {
        return pacienteService.buscarPorDocumento(numeroDocumento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}