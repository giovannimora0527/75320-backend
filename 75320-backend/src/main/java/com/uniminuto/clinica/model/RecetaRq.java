package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Request para crear o actualizar una receta médica.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaRq implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador de la receta (solo para actualización) */
    private Integer id;

    /** Identificador de la cita asociada a la receta */
    @NotNull(message = "El identificador de la cita es obligatorio.")
    private Integer citaId;

    /** Identificador del medicamento recetado */
    @NotNull(message = "El identificador del medicamento es obligatorio.")
    private Integer medicamentoId;

    /** Dosis prescrita del medicamento */
    @NotBlank(message = "La dosis es obligatoria.")
    private String dosis;

    /** Indicaciones adicionales para el uso del medicamento */
    @NotBlank(message = "Las indicaciones son obligatorias.")
    private String indicaciones;

    /** Fecha y hora en que se emitió la receta */
    private LocalDateTime fechaHora;
}
