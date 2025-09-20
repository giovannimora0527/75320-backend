/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;  
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import javax.persistence.JoinColumn;
/**
 *
 * @author Alkri
 */

@Data
@Entity

@Table(name="cita")
public class Cita implements Serializable{
    /**
     * Serializable
     */
    public static final long serialVersionUID = 1L;
    
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    /**
     * campos
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private  Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private  Medico medico;
    
    @Column(name = "fecha_hora")
    private  LocalDateTime fechaHora;
    
    @Column(name = "estado")
    private  String estado ; 
    
    @Column(name = "motivo")
    private  String motivo ;
    
}