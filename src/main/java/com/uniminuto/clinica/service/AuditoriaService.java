package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.LogsAuditoriaResponseRs;
import com.uniminuto.clinica.utils.BadRequestException;

import java.time.LocalDate;

/**
 * Servicio para la gestión de logs de auditoría.
 * Proporciona métodos para consultar registros de auditoría con paginación y filtros.
 */
public interface AuditoriaService {

    /**
     * Lista los logs de auditoría con paginación y filtros opcionales.
     * 
     * @param pagina Número de página (comienza en 1)
     * @param tamanoPagina Tamaño de la página
     * @param fechaInicio Fecha de inicio para filtrar (opcional)
     * @param fechaFin Fecha de fin para filtrar (opcional)
     * @param usuario Nombre de usuario para filtrar (opcional)
     * @param tipoEvento Tipo de evento para filtrar (opcional: LOGIN_EXITOSO, LOGIN_FALLIDO, RECUPERACION_PASSWORD)
     * @return Respuesta paginada con los logs de auditoría
     * @throws BadRequestException si los parámetros son inválidos
     */
    LogsAuditoriaResponseRs listarLogs(
            Integer pagina,
            Integer tamanoPagina,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            String usuario,
            String tipoEvento
    ) throws BadRequestException;
}

