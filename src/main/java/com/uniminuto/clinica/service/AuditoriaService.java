package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.AuditoriaFiltroRq;
import com.uniminuto.clinica.model.AuditoriaListaRs;

/**
 * Servicio para consultar registros de auditoría.
 * 
 * @author Sistema
 */
public interface AuditoriaService {
    
    /**
     * Lista registros de auditoría aplicando los filtros especificados.
     * 
     * @param filtro Filtros de búsqueda y paginación
     * @return Lista paginada de registros de auditoría
     */
    AuditoriaListaRs listarAuditoria(AuditoriaFiltroRq filtro);
}

