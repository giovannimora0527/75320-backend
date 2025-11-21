package com.uniminuto.clinica.model;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Request para consultar logs de auditoría con filtros y paginación.
 * 
 * @author Sistema
 */
@Data
public class AuditoriaLoginRq implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Nombre de usuario para filtrar (opcional).
     */
    private String username;
    
    /**
     * Fecha de inicio del rango (opcional).
     */
    private LocalDateTime fechaDesde;
    
    /**
     * Fecha de fin del rango (opcional).
     */
    private LocalDateTime fechaHasta;
    
    /**
     * Filtrar por tipo de evento: true = exitosos, false = fallidos, null = todos.
     */
    private Boolean exitoso;
    
    /**
     * Número de página (base 0).
     */
    private Integer pagina = 0;
    
    /**
     * Tamaño de página (número de registros por página).
     */
    private Integer tamano = 20;
    
    /**
     * Campo por el cual ordenar (por defecto: fechaHora).
     */
    private String ordenarPor = "fechaHora";
    
    /**
     * Dirección del ordenamiento: ASC o DESC (por defecto: DESC).
     */
    private String direccion = "DESC";
}

