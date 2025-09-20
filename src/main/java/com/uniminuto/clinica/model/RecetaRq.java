/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.model;

import java.time.LocalDateTime;
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
