package com.uniminuto.clinica.entity;

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
}
