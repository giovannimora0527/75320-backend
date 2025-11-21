package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author PC
 */

@Data
@Entity
@Table(name = "cita")
public class Cita implements Serializable{
    /**
     * Id serializable.
     */    
    private static final long serialVersionUID = 1L;
    
    /**
     * ID de la cita
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    /**
     * ID del Paciente
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    /**
     * ID del medico
     */
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    /**
     * Fecha y hora de la cita
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Estado de la cita
     */
    @Column(length = 20)
    private String estado;

    /**
     * Motivo de la cita
     */
    @Column(columnDefinition = "TEXT")
    private String motivo;
    
}
