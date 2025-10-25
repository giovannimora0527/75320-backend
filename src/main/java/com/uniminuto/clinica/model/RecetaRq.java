package com.uniminuto.clinica.model;
import java.time.LocalDate;


import lombok.Data;

@Data
public class RecetaRq {
    private Integer citaId;
    private Integer medicamentoId;
    private String dosis;
    private String indicaciones;
    private LocalDate fecha_hora;
}
