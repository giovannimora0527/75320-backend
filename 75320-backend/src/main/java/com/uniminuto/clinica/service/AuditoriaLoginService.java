package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.AuditoriaLoginRq;
import com.uniminuto.clinica.model.AuditoriaLoginRs;

/**
 * Servicio para consultar logs de auditoría de inicio de sesión.
 * 
 * @author Sistema
 */
public interface AuditoriaLoginService {
    
    /**
     * Consulta los registros de auditoría con filtros y paginación.
     * 
     * @param request Filtros y parámetros de paginación.
     * @return Respuesta paginada con los registros de auditoría.
     */
    AuditoriaLoginRs consultarAuditoria(AuditoriaLoginRq request);
}

