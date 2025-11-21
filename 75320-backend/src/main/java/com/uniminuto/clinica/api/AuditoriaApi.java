package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AuditoriaLoginRq;
import com.uniminuto.clinica.model.AuditoriaLoginRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API para consultar logs de auditoría de inicio de sesión.
 * 
 * Endpoints:
 *  - POST /auditoria/consultar → Consulta logs con filtros y paginación
 * 
 * @author Sistema
 */
@Tag(name = "Auditoría", description = "Consulta de logs de auditoría de inicio de sesión")
@CrossOrigin(origins = "*")
@RequestMapping("/auditoria")
public interface AuditoriaApi {
    
    /**
     * Consulta los registros de auditoría con filtros y paginación.
     * 
     * Permite filtrar por:
     * - Usuario (búsqueda parcial, case-insensitive)
     * - Rango de fechas (fechaDesde y fechaHasta)
     * - Tipo de evento (exitoso: true/false/null para todos)
     * 
     * Soporta paginación con:
     * - Número de página (base 0)
     * - Tamaño de página (máximo 100)
     * - Ordenamiento por campo y dirección (ASC/DESC)
     * 
     * @param request Filtros y parámetros de paginación.
     * @return Respuesta paginada con los registros de auditoría.
     */
    @Operation(
        summary = "Consultar logs de auditoría",
        description = "Consulta los registros de auditoría de inicio de sesión con filtros opcionales " +
                     "por usuario, rango de fechas y tipo de evento. Soporta paginación y ordenamiento."
    )
    @PostMapping(
        value = "/consultar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<AuditoriaLoginRs> consultarAuditoria(@RequestBody AuditoriaLoginRq request);
}

