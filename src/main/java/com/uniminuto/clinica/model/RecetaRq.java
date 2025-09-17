
package com.uniminuto.clinica.model;

import lombok.Data;

/**
 *
 * @author Andre
 */


/**
 * Objeto de entrada para el JSON
 */

@Data
public class RecetaRq {
    
    
    private Long id; 
    
    private Long citaId; 
    
    private Integer medicamentoId; 
    
    private String dosis; 
    
    private String indicaciones;
    
}
