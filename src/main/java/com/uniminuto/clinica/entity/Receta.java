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
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author DELL
 */
@Data
@Entity
@Table(name = "receta")
public class Receta implements Serializable{
    /**
     * Id serializable.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * citad_id.
     */
    @Column(name = "cita_id")
    private Cita cita;
    /**
     * medicamento_id.
     */
    @Column(name = "medicamento_id")
    private Medicamento medicamento;
    /**
     * dosis.
     */
    @Column(name = "dosis")
    private String dosis;    
    /**
     * indicaciones.
     */
    @Column(name = "indicaciones")
    private String indicaciones;
    /**
     * fecha_creacion_registro.
     */
    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro;
}