package com.uniminuto.clinica.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecetaRs {
    
    private Integer id;
    
    private Integer citaId;
    
    private Integer medicamentoId;
    
    private String dosis;
    
    private String indicaciones;
    
    private LocalDateTime fechaCreacionRegistro;
    
}