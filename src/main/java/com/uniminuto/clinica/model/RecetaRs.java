package com.uniminuto.clinica.model;

import java.time.LocalDateTime;
import lombok.Data;

//DTO que envia la información de una receta en una respuesta de una API REST
@Data
public class RecetaRs {
    
    private Integer id;
    
    private Integer citaId;
    
    private Integer medicamentoId;
    
    private String dosis;
    
    private String indicaciones;
    
    private LocalDateTime fechaCreacionRegistro;
    
}