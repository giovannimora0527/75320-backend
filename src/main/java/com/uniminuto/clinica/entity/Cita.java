package com.uniminuto.clinica.entity;

<<<<<<< HEAD
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad que representa una cita médica en el sistema.
 * Corresponde a la tabla 'cita' en la base de datos.
 */
@Entity
@Table(name = "cita")
@Data
public class Cita implements Serializable {
    /** Identificador único de la cita */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Identificador del paciente asociado a la cita */
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    /** Identificador del médico asociado a la cita */
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    /** Fecha y hora de la cita */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /** Estado de la cita (ejemplo: programada, realizada, cancelada) */
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    /** Motivo de la cita */
    @Column(name = "motivo")
    private String motivo;
=======
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cita")
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la cita.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha y hora de la cita.
     */
    @Column(name = "fecha_cita")
    private LocalDateTime fechaCita;

    /**
     * Motivo de la cita.
     */
    @Column(name = "motivo")
    private String motivo;

    /**
     * Relación con el paciente.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    /**
     * Relación con el médico.
     */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
>>>>>>> origin/Mariana_976621Castillo
}
