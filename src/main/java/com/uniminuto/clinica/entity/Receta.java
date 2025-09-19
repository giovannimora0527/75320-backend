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
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa una receta médica en el sistema de clínica.
 * Una receta está asociada a una cita médica y un medicamento específico.
 *
 * @author anago
 */
@Getter
@Setter
@Table(name = "receta", schema = "clinica")
public class Receta implements Serializable {

    /**
     * Id serializable.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la receta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Cita médica asociada a esta receta.
     */
    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;
    /**
     * Medicamento prescrito en la receta.
     */

    /**
     * Dosis del medicamento.
     */
    @Column(name = "dosis", nullable = false, length = 100)
    public String dosis;

    /**
     * Indicaciones para el uso del medicamento.
     */
    @Column(name = "indicaciones", length = 500)
    public String indicaciones;

    /**
     * Fecha y hora de creación del registro de la receta.
     */
    @Column(name = "fecha_creacion_registro", nullable = false)
    private LocalDateTime fechaCreacionRegistro = LocalDateTime.now();

    /**
     * Constructor por defecto que inicializa la fecha de creación.
     */
    public Receta() {
        this.fechaCreacionRegistro = LocalDateTime.now();
    }

    public void setFechaCreacionRegistro(LocalDateTime now) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getdosis() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setDosis(String dosis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Constructor con parámetros básicos.
     *
     * @param cita Cita asociada a la receta
     * @param medicamento Medicamento prescrito
     * @param dosis Dosis del medicamento
     * @param indicaciones Indicaciones para el paciente
     */
  
    
}