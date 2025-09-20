/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author Andre
 */

@Data
@Entity
@Table(name="Receta")
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
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "cita_id")
    private Cita cita; 
    
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento; 
    
    @Column(name = "dosis")
    private String dosis; 
    
    @Column(name = "indicaciones")
    private String indicaciones; 
    
    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro;
    
  
}