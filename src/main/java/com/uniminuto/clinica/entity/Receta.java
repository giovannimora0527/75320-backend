/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Data;

/**
 *
 * @author Oskr
 */

@Entity
@Table(name="receta")
@Data
public class Receta {
          
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "cita_id",nullable = false)
    private Cita cita;
    
    @ManyToOne
    @JoinColumn(name = "medicamento_id",nullable = false)
    private Medicamento medicamento;
    
    
    @Column(name="fecha_creacion_registro",updatable = false, nullable = false)
    private LocalDateTime fechaRegistro;
            
    @Column
    private String dosis;
    
    @Column 
    private String indicaciones;
    
    @PrePersist
    public void prePersist() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
    
        }

    }
    @PreUpdate
    public void preUpdate() {
        fechaRegistro = LocalDateTime.now();
    }

}