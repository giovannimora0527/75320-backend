package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.LogsAuditoriaResponseRs;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;

/**
 * API para la gestión de logs de auditoría.
 * 
 * Endpoints:
 *  - GET /auditoria/listar → Lista logs de auditoría con paginación y filtros.
 *  - GET /auditoria/tipos-eventos → Lista los tipos de eventos disponibles.
 * 
 * @author Sistema
 */
@CrossOrigin(origins = "*")
@RequestMapping("/auditoria")
public interface AuditoriaApi {

    /**
     * Lista los logs de auditoría con paginación y filtros opcionales.
     * 
     * @param pagina Número de página (comienza en 1, por defecto 1)
     * @param tamanoPagina Tamaño de la página (por defecto 20, máximo 100)
     * @param fechaInicio Fecha de inicio para filtrar (formato: YYYY-MM-DD)
     * @param fechaFin Fecha de fin para filtrar (formato: YYYY-MM-DD)
     * @param usuario Nombre de usuario para filtrar (búsqueda parcial)
     * @param tipoEvento Tipo de evento para filtrar (LOGIN_EXITOSO, LOGIN_FALLIDO, RECUPERACION_PASSWORD, RECUPERACION_PASSWORD_FALLIDA)
     * @return Respuesta paginada con los logs de auditoría
     * @throws BadRequestException si los parámetros son inválidos
     */
    @GetMapping(
        value = "/listar",
        produces = "application/json"
    )
    ResponseEntity<LogsAuditoriaResponseRs> listarLogs(
        @RequestParam(required = false, defaultValue = "1") Integer pagina,
        @RequestParam(required = false, defaultValue = "20") Integer tamanoPagina,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
        @RequestParam(required = false) String usuario,
        @RequestParam(required = false) String tipoEvento
    ) throws BadRequestException;

    /**
     * Obtiene la lista de tipos de eventos disponibles para filtrar.
     * 
     * @return Lista de tipos de eventos disponibles
     */
    @GetMapping(
        value = "/tipos-eventos",
        produces = "application/json"
    )
    ResponseEntity<List<String>> obtenerTiposEventos();
}

