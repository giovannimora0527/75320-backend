package com.uniminuto.clinica.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Entidad que representa una cita médica en el sistema.
 *
 * @author
 */
@Data
@Entity
@Table(name = "cita")
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID de la cita (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Paciente asociado a la cita.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    /**
     * Médico asignado a la cita.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    /**
     * Fecha y hora de la cita.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Estado actual de la cita (Ej: "Programada", "Completada", "Cancelada").
     */
    @Column(name = "estado", length = 20)
    private String estado;

    /**
     * Motivo o descripción de la cita médica.
     */
    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;
}
