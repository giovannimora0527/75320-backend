package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AuditoriaFiltroRq;
import com.uniminuto.clinica.model.AuditoriaListaRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API para consultar registros de auditoría.
 * Permite consultar logs de login y recuperación de contraseña con paginación y filtros.
 * 
 * Endpoints:
 *  - POST /auditoria/listar → Lista registros de auditoría con filtros y paginación.
 * 
 * @author Sistema
 */
@CrossOrigin(origins = "*")
@RequestMapping("/auditoria")
public interface AuditoriaApi {
    
    /**
     * Lista registros de auditoría con filtros opcionales y paginación.
     * 
     * @param filtro Filtros de búsqueda (fecha, usuario, tipo de evento, etc.)
     * @return Lista paginada de registros de auditoría
     */
    @PostMapping("/listar")
    ResponseEntity<AuditoriaListaRs> listarAuditoria(@RequestBody AuditoriaFiltroRq filtro);
}

