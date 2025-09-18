/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa una cita médica en el sistema
 * @author diego
 */


@Data
@Entity
@Table(name = "cita")
public class Cita implements Serializable {

    /**
     * Id serializable.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la cita.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Paciente al que pertenece la cita.
     */
    @JoinColumn(name = "paciente_id")
    @ManyToOne
    private Paciente paciente;

    /**
     * Médico asignado a la cita.
     */
    
    @JoinColumn(name = "medico_id")
    @ManyToOne
    private Medico medico;

    /**
     * Fecha y hora de la cita.
     */
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    /**
     * Estado de la cita (por ejemplo: PENDIENTE, CONFIRMADA, CANCELADA).
     */
    @Column(name = "estado")
    private String estado;

    /**
     * Motivo de la cita.
     */
    @Column(name = "motivo")
    private String motivo;
}