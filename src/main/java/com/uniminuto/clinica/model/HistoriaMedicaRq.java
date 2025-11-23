package com.uniminuto.clinica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * DTO para solicitudes de historias médicas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaMedicaRq {

    @NotNull(message = "El ID del paciente es obligatorio")
    private Integer pacienteId;

    @NotNull(message = "El ID del médico es obligatorio")
    private Integer medicoId;

    @NotNull(message = "La fecha de consulta es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaConsulta;

    @NotBlank(message = "El motivo de consulta es obligatorio")
    private String motivoConsulta;

    @NotBlank(message = "Los síntomas son obligatorios")
    private String sintomas;

    @NotBlank(message = "El diagnóstico es obligatorio")
    private String diagnostico;

    @NotBlank(message = "El tratamiento es obligatorio")
    private String tratamiento;

    private String observaciones;
}

