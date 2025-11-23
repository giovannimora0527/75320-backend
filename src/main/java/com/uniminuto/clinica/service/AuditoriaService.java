package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.AuditoriaRs;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**
 * Servicio para la gestión de registros de auditoría.
 * Proporciona métodos para consultar logs de seguridad con filtros y paginación.
 */
public interface AuditoriaService {
    
    /**
     * Lista los registros de auditoría de login con filtros opcionales.
     * 
     * @param username Filtro por nombre de usuario.
     * @param exitoso Filtro por éxito/fallo.
     * @param fechaDesde Filtro por fecha desde.
     * @param fechaHasta Filtro por fecha hasta.
     * @param pageable Configuración de paginación.
     * @return Página de registros de auditoría de login.
     * @throws BadRequestException Si hay un error en los parámetros.
     */
    Page<AuditoriaRs> listarAuditoriaLogin(
            String username,
            Boolean exitoso,
            LocalDateTime fechaDesde,
            LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException;
    
    /**
     * Lista los registros de auditoría de recuperación de contraseña con filtros opcionales.
     * 
     * @param username Filtro por nombre de usuario.
     * @param exitoso Filtro por éxito/fallo.
     * @param fechaDesde Filtro por fecha desde.
     * @param fechaHasta Filtro por fecha hasta.
     * @param pageable Configuración de paginación.
     * @return Página de registros de auditoría de recuperación.
     * @throws BadRequestException Si hay un error en los parámetros.
     */
    Page<AuditoriaRs> listarAuditoriaRecuperacion(
            String username,
            Boolean exitoso,
            LocalDateTime fechaDesde,
            LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException;
}

