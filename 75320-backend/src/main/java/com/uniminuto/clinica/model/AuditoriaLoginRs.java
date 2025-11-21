package com.uniminuto.clinica.model;

import com.uniminuto.clinica.entity.AuditoriaLogin;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Response paginado para consultas de auditoría de login.
 * 
 * @author Sistema
 */
@Data
public class AuditoriaLoginRs implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Lista de registros de auditoría.
     */
    private List<AuditoriaLogin> contenido;
    
    /**
     * Número total de elementos.
     */
    private Long totalElementos;
    
    /**
     * Número total de páginas.
     */
    private Integer totalPaginas;
    
    /**
     * Número de página actual (base 0).
     */
    private Integer paginaActual;
    
    /**
     * Tamaño de página.
     */
    private Integer tamanoPagina;
    
    /**
     * Indica si hay más páginas.
     */
    private Boolean tieneSiguiente;
    
    /**
     * Indica si hay página anterior.
     */
    private Boolean tieneAnterior;
}

