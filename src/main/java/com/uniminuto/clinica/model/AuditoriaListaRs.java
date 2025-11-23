package com.uniminuto.clinica.model;

import lombok.Data;
import java.util.List;

/**
 * Modelo de respuesta para la lista paginada de registros de auditoría.
 * 
 * @author Sistema
 */
@Data
public class AuditoriaListaRs {
    
    /**
     * Lista de registros de auditoría.
     */
    private List<AuditoriaRs> registros;
    
    /**
     * Número total de registros que coinciden con los filtros.
     */
    private Long totalRegistros;
    
    /**
     * Número de página actual (0-indexed).
     */
    private Integer paginaActual;
    
    /**
     * Tamaño de página utilizado.
     */
    private Integer tamanoPagina;
    
    /**
     * Número total de páginas.
     */
    private Integer totalPaginas;
}

