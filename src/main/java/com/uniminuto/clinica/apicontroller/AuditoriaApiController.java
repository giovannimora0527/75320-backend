package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AuditoriaApi;
import com.uniminuto.clinica.model.LogsAuditoriaResponseRs;
import com.uniminuto.clinica.service.AuditoriaService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Controlador REST para la gestión de logs de auditoría.
 * Implementa la interfaz AuditoriaApi.
 *
 * Endpoints:
 *  - GET  /auditoria/listar → Lista logs de auditoría con paginación y filtros
 *  - GET  /auditoria/tipos-eventos → Lista los tipos de eventos disponibles
 *
 * @author Sistema
 */
@RestController
public class AuditoriaApiController implements AuditoriaApi {

    private final AuditoriaService auditoriaService;

    // ✅ Inyección por constructor (mejor práctica)
    public AuditoriaApiController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    /**
     * Lista los logs de auditoría con paginación y filtros opcionales.
     */
    @Override
    public ResponseEntity<LogsAuditoriaResponseRs> listarLogs(
            Integer pagina,
            Integer tamanoPagina,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            String usuario,
            String tipoEvento
    ) throws BadRequestException {
        LogsAuditoriaResponseRs respuesta = auditoriaService.listarLogs(
                pagina,
                tamanoPagina,
                fechaInicio,
                fechaFin,
                usuario,
                tipoEvento
        );
        return ResponseEntity.ok(respuesta);
    }

    /**
     * Obtiene la lista de tipos de eventos disponibles para filtrar.
     */
    @Override
    public ResponseEntity<List<String>> obtenerTiposEventos() {
        List<String> tiposEventos = Arrays.asList(
            "LOGIN_EXITOSO",
            "LOGIN_FALLIDO",
            "RECUPERACION_PASSWORD",
            "RECUPERACION_PASSWORD_FALLIDA"
        );
        return ResponseEntity.ok(tiposEventos);
    }
}

