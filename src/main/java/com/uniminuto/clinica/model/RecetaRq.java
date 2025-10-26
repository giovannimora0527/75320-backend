package com.uniminuto.clinica.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class RecetaRq {
    /**
     * Otros campos para el objeto de entrada
     */
    private Long id;

    @NotBlank(message = "El id de cita es obligatorio")
    private Long cita;

    @NotBlank(message = "El id de medicamento es obligatorio")
    private Integer medicamento;

    @NotBlank(message = "La dosis es obligatoria")
    private String dosis;

    @NotBlank(message = "Las indicaciones son obligatorias")
    private String indicaciones;
}