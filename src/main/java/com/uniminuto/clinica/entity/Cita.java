/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.entity;

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
}