package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AuditoriaRs;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * API para consultar los registros de auditoría del sistema.
 * Permite consultar logs de login y recuperación de contraseña con filtros y paginación.
 */
@RequestMapping("/auditoria")
public interface AuditoriaApi {
    
    /**
     * Obtiene los registros de auditoría de login con filtros opcionales.
     * 
     * @param username Filtro por nombre de usuario (opcional).
     * @param exitoso Filtro por éxito/fallo (opcional).
     * @param fechaDesde Filtro por fecha desde (opcional).
     * @param fechaHasta Filtro por fecha hasta (opcional).
     * @param pageable Configuración de paginación.
     * @return Página de registros de auditoría de login.
     * @throws BadRequestException Si hay un error en los parámetros.
     */
    @GetMapping("/login")
    ResponseEntity<Page<AuditoriaRs>> listarAuditoriaLogin(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Boolean exitoso,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException;
    
    /**
     * Obtiene los registros de auditoría de recuperación de contraseña con filtros opcionales.
     * 
     * @param username Filtro por nombre de usuario (opcional).
     * @param exitoso Filtro por éxito/fallo (opcional).
     * @param fechaDesde Filtro por fecha desde (opcional).
     * @param fechaHasta Filtro por fecha hasta (opcional).
     * @param pageable Configuración de paginación.
     * @return Página de registros de auditoría de recuperación.
     * @throws BadRequestException Si hay un error en los parámetros.
     */
    @GetMapping("/recuperacion")
    ResponseEntity<Page<AuditoriaRs>> listarAuditoriaRecuperacion(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Boolean exitoso,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException;
}

