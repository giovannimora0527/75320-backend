package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.CitaService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST que implementa la API de Citas.
 * 
 * Endpoints:
 *  - GET  /cita/listar → Lista todas las citas.
 *  - GET  /cita/listar-por-paciente → Lista citas de un paciente específico.
 *  - POST /cita/guardar → Crea una nueva cita.
 *  - POST /cita/actualizar → Actualiza una cita existente.
 *  - POST /cita/eliminar → Elimina una cita por su ID.
 * 
 * Maneja las operaciones relacionadas con las citas médicas.
 * 
 * @author 
 */
@RestController
@RequestMapping("/cita")
public class CitaApiController implements CitaApi {

    private final CitaService citaService;

    // ✅ Constructor de inyección de dependencias
    public CitaApiController(CitaService citaService) {
        this.citaService = citaService;
    }

    /**
     * Lista todas las citas del sistema.
     */
    @Override
    @GetMapping("/listar")
    public ResponseEntity<List<Cita>> listarCitas() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    /**
     * Lista las citas asociadas a un paciente específico.
     */
    @Override
    @GetMapping("/listar-por-paciente")
    public ResponseEntity<List<Cita>> listarCitasPorPaciente(@RequestParam Integer pacienteId)
            throws BadRequestException {
        return ResponseEntity.ok(citaService.listarCitasPorPaciente(pacienteId));
    }

    /**
     * Guarda una nueva cita.
     */
    @Override
    @PostMapping("/guardar")
    public ResponseEntity<RespuestaRs> guardarCita(@Valid @RequestBody CitaRq citaRq) {
        try {
            return ResponseEntity.ok(citaService.guardarCita(citaRq));
        } catch (Exception e) {
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(400);
            respuesta.setMensaje("Error al crear la cita: " + e.getMessage());
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

    /**
     * Actualiza una cita existente.
     */
    @Override
    @PostMapping("/actualizar")
    public ResponseEntity<RespuestaRs> actualizarCita(@RequestParam Integer id, @RequestBody CitaRq citaRq)
            throws BadRequestException {
        return ResponseEntity.ok(citaService.actualizarCita(id, citaRq));
    }

    /**
     * Elimina una cita por su ID.
     */
    @Override
    @PostMapping("/eliminar")
    public ResponseEntity<RespuestaRs> eliminarCita(@RequestParam Integer id) throws BadRequestException {
        return ResponseEntity.ok(citaService.eliminarCita(id));
    }

    /**
     * Lista todas las citas ordenadas por fecha y hora.
     * Similar a listarCitas() pero con un endpoint específico.
     */
    @Override
    @GetMapping("/por-fechahora")
    public ResponseEntity<List<Cita>> listarCitaPorFechaHora() {
        return ResponseEntity.ok(citaService.listarCitas());
    }
}
