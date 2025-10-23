package com.uniminuto.clinica.model;

import lombok.Data;

@Data
public class RecetaRq {
    private Integer citaId;
    private Integer medicamentoId;
    private String dosis;
    private String indicaciones;
}
