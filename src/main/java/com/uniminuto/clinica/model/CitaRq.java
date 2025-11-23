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
 * Request para crear o actualizar una cita médica.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaRq implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador del paciente asociado a la cita */
    @NotNull(message = "El identificador del paciente es obligatorio.")
    private Integer pacienteId;

    /** Identificador del médico que atenderá la cita */
    @NotNull(message = "El identificador del médico es obligatorio.")
    private Integer medicoId;

    /** Fecha y hora programada para la cita */
    @NotNull(message = "La fecha y hora de la cita es obligatoria.")
    private LocalDateTime fechaHora;

    /** Estado actual de la cita (programada, cancelada, completada, etc.) */
    @NotBlank(message = "El estado de la cita es obligatorio.")
    private String estado;

    /** Motivo o razón de la cita */
    @NotBlank(message = "El motivo de la cita es obligatorio.")
    private String motivo;
}
