package com.uniminuto.clinica.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RecetaRq {
    @NotNull(message = "El campo citaId es obligatorio")
    private Long citaId;

    @NotNull(message = "El campo medicamentoId es obligatorio")
    private Integer medicamentoId;

    @NotBlank(message = "El campo dosis es obligatorio")
    private String dosis;

    @NotBlank(message = "El campo indicaciones es obligatorio")
    private String indicaciones;
}
