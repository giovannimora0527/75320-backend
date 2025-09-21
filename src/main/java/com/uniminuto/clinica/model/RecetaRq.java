package com.uniminuto.clinica.model;

import lombok.Data;

/**
 * Se utiliza como objeto de entrada en los controladores REST.
 */
/**
 * @author Anderson
 */

@Data
public class RecetaRq {
    /**
     * Otros campos para el objeto de entrada
     */
    private Long id; 
    
    private Long citaId; 
    
    private Integer medicamentoId; 
    
    private String dosis; 
    
    private String indicaciones;
}