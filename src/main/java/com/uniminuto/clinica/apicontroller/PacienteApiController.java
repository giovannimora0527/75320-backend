package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de pacientes.
 *
 * @author lmora
 */
@RestController
public class PacienteApiController implements PacienteApi {

    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.encontrarTodosLosPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @Override
    public ResponseEntity<List<Paciente>> listarPacientesActivos() {
        List<Paciente> pacientes = pacienteService.encontrarPacientesActivos();
        return ResponseEntity.ok(pacientes);
    }

    @Override
    public ResponseEntity<Paciente> buscarPacientePorId(Long id) {
        try {
            Paciente paciente = pacienteService.encontrarPacientePorId(id)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
            return ResponseEntity.ok(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Paciente> buscarPacientePorDocumento(String numeroDocumento) {
        try {
            Paciente paciente = pacienteService.encontrarPacientePorDocumento(numeroDocumento)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con documento: " + numeroDocumento));
            return ResponseEntity.ok(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Paciente>> buscarPacientesPorNombre(String nombre) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorNombre(nombre);
        return ResponseEntity.ok(pacientes);
    }

    @Override
    public ResponseEntity<List<Paciente>> buscarPacientesPorTipoDocumento(String tipoDocumento) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorTipoDocumento(tipoDocumento);
        return ResponseEntity.ok(pacientes);
    }

    @Override
    public ResponseEntity<List<Paciente>> buscarPacientesPorEdad(int edadMinima, int edadMaxima) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorRangoEdad(edadMinima, edadMaxima);
        return ResponseEntity.ok(pacientes);
    }

    @Override
    public ResponseEntity<Paciente> crearPaciente(Paciente paciente) {
        try {
            Paciente pacienteCreado = pacienteService.guardarPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Paciente> actualizarPaciente(Long id, Paciente paciente) {
        try {
            // Asegurar que el ID del path coincida con el ID del objeto
            paciente.setId(id);
            Paciente pacienteActualizado = pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> eliminarPaciente(Long id) {
        try {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Paciente> desactivarPaciente(Long id) {
        try {
            Paciente paciente = pacienteService.desactivarPaciente(id);
            return ResponseEntity.ok(paciente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Paciente> activarPaciente(Long id) {
        try {
            Paciente paciente = pacienteService.activarPaciente(id);
            return ResponseEntity.ok(paciente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Long> contarPacientesActivos() {
        long conteo = pacienteService.contarPacientesActivos();
        return ResponseEntity.ok(conteo);
    }

    @Override
    public ResponseEntity<List<Paciente>> listarPorFechaNacimientoDesc() {
       return ResponseEntity.ok(pacienteService.listarPorFechaNacimientoDesc());
    }
}
