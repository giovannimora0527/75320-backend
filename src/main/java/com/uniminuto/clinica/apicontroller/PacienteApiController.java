package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.PacienteService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST que implementa la API de Pacientes.
 *
 * Endpoints principales:
 *  - GET  /paciente/listar → Lista todos los pacientes.
 *  - GET  /paciente/buscar → Busca un paciente por número de documento.
 *  - POST /paciente/guardar → Registra un nuevo paciente.
 *  - PUT  /paciente/actualizar/{id} → Actualiza un paciente existente.
 *  - DELETE /paciente/eliminar/{id} → Elimina un paciente.
 *  - GET  /paciente/listar-por-edad → Lista pacientes ordenados por edad.
 * 
 * @author 
 */
@RestController
public class PacienteApiController implements PacienteApi {

    private final PacienteService pacienteService;

    // ✅ Inyección por constructor (mejor que @Autowired)
    public PacienteApiController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Lista todos los pacientes.
     */
    @Override
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.encontrarTodosLosPacientes());
    }

    /**
     * Busca un paciente por su número de documento.
     */
    @Override
    public ResponseEntity<Paciente> buscarPacienteXIdentificacion(String numeroDocumento)
            throws BadRequestException {
        try {
            return ResponseEntity.ok(pacienteService.buscarPacientePorDocumento(numeroDocumento));
        } catch (org.springframework.web.server.ResponseStatusException e) {
            throw new BadRequestException(e.getReason());
        }
    }

    /**
     * Guarda un nuevo paciente en la base de datos.
     */
    @Override
    public ResponseEntity<RespuestaRs> guardarPaciente(PacienteRq pacienteRq)
            throws BadRequestException {
        return ResponseEntity.ok(pacienteService.guardarPaciente(pacienteRq));
    }

    /**
     * Actualiza los datos de un paciente existente.
     */
    @Override
    public ResponseEntity<RespuestaRs> actualizarPaciente(Integer id, PacienteRq pacienteRq)
            throws BadRequestException {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, pacienteRq));
    }

    /**
     * Elimina un paciente por su ID.
     */
    @Override
    public ResponseEntity<RespuestaRs> eliminarPaciente(Integer id)
            throws BadRequestException {
        return ResponseEntity.ok(pacienteService.eliminarPaciente(id));
    }

    /**
     * Lista los pacientes ordenados por edad.
     */
    @Override
    public ResponseEntity<List<Paciente>> listarPacientesPorEdad() {
        return ResponseEntity.ok(pacienteService.listarPacientesPorEdad());
    }

    /**
     * Lista los pacientes ordenados por fecha de nacimiento según el orden especificado.
     */
    @Override
    public ResponseEntity<List<Paciente>> listarPacientesXOrden(String orden) {
        boolean ascendente = "asc".equalsIgnoreCase(orden) || "ascendente".equalsIgnoreCase(orden);
        return ResponseEntity.ok(pacienteService.listarOrdenadoPorFechaNacimiento(ascendente));
    }
}
