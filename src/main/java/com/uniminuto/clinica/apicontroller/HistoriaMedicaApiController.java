package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.HistoriaMedicaApi;
import com.uniminuto.clinica.entity.HistoriaMedica;
import com.uniminuto.clinica.model.HistoriaMedicaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.HistoriaMedicaService;
import com.uniminuto.clinica.utils.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la gestión de historias médicas.
 * Implementa la interfaz HistoriaMedicaApi.
 */
@RestController
public class HistoriaMedicaApiController implements HistoriaMedicaApi {

    private final HistoriaMedicaService historiaMedicaService;

    public HistoriaMedicaApiController(HistoriaMedicaService historiaMedicaService) {
        this.historiaMedicaService = historiaMedicaService;
    }

    @Override
    public ResponseEntity<List<HistoriaMedica>> listarHistorias() {
        try {
            List<HistoriaMedica> historias = historiaMedicaService.listarHistorias();
            return ResponseEntity.ok(historias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<HistoriaMedica> obtenerHistoria(Integer id) throws BadRequestException {
        try {
            HistoriaMedica historia = historiaMedicaService.obtenerPorId(id);
            return ResponseEntity.ok(historia);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<HistoriaMedica>> listarPorPaciente(Integer pacienteId) {
        try {
            List<HistoriaMedica> historias = historiaMedicaService.listarPorPaciente(pacienteId);
            return ResponseEntity.ok(historias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarHistoria(@Valid @RequestBody HistoriaMedicaRq historiaMedicaRq)
            throws BadRequestException {
        try {
            return ResponseEntity.ok(historiaMedicaService.guardarHistoria(historiaMedicaRq));
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje("Error al guardar la historia médica: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Override
    public ResponseEntity<RespuestaRs> actualizarHistoria(
            Integer id,
            @Valid @RequestBody HistoriaMedicaRq historiaMedicaRq) {
        try {
            return ResponseEntity.ok(historiaMedicaService.actualizarHistoria(id, historiaMedicaRq));
        } catch (BadRequestException e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            e.printStackTrace();
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje("Error al actualizar la historia médica: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Override
    public ResponseEntity<RespuestaRs> eliminarHistoria(Integer id) {
        try {
            return ResponseEntity.ok(historiaMedicaService.eliminarHistoria(id));
        } catch (BadRequestException e) {
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            e.printStackTrace();
            RespuestaRs error = new RespuestaRs();
            error.setStatus(400);
            error.setMensaje("Error al eliminar la historia médica: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}

