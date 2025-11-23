package com.uniminuto.clinica.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad que representa una historia médica en el sistema.
 * Cada historia médica está asociada a un paciente y un médico.
 */
@Data
@Entity
@Table(name = "historia_medica")
public class HistoriaMedica implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la historia médica.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Paciente asociado a la historia médica.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Paciente paciente;

    /**
     * Médico que atendió al paciente.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Medico medico;

    /**
     * Fecha de la consulta.
     */
    @Column(name = "fecha_consulta", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaConsulta;

    /**
     * Motivo de la consulta.
     */
    @Column(name = "motivo_consulta", columnDefinition = "TEXT", nullable = false)
    private String motivoConsulta;

    /**
     * Síntomas presentados por el paciente.
     */
    @Column(name = "sintomas", columnDefinition = "TEXT", nullable = false)
    private String sintomas;

    /**
     * Diagnóstico realizado.
     */
    @Column(name = "diagnostico", columnDefinition = "TEXT", nullable = false)
    private String diagnostico;

    /**
     * Tratamiento prescrito.
     */
    @Column(name = "tratamiento", columnDefinition = "TEXT", nullable = false)
    private String tratamiento;

    /**
     * Observaciones adicionales.
     */
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    /**
     * Fecha y hora de creación del registro.
     */
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    /**
     * Fecha y hora de última modificación.
     */
    @Column(name = "fecha_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaActualizacion;
}

