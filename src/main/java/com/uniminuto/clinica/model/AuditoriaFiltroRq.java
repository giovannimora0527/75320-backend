package com.uniminuto.clinica.model;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Modelo de solicitud para filtrar registros de auditoría.
 * Permite filtrar por fecha, usuario y tipo de evento.
 * 
 * @author Sistema
 */
@Data
public class AuditoriaFiltroRq {
    
    /**
     * Nombre de usuario para filtrar (opcional).
     */
    private String username;
    
    /**
     * Fecha de inicio para el rango de búsqueda (opcional).
     */
    private LocalDateTime fechaDesde;
    
    /**
     * Fecha de fin para el rango de búsqueda (opcional).
     */
    private LocalDateTime fechaHasta;
    
    /**
     * Tipo de evento: "login" o "recuperacion" (opcional).
     */
    private String tipoEvento;
    
    /**
     * Indica si se filtra solo por intentos exitosos (opcional).
     */
    private Boolean exitoso;
    
    /**
     * Número de página para paginación (por defecto 0).
     */
    private Integer pagina = 0;
    
    /**
     * Tamaño de página para paginación (por defecto 10).
     */
    private Integer tamano = 10;
}

