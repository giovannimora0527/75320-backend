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
@Table(name = "receta")
public class Receta implements Serializable{
    /**
     * Id serializable.
     */    
    private static final long serialVersionUID = 1L;
    
    /**
     * ID de la receta
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * ID de la cita
     */
    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;
    
    /**
     * ID del medicamento
     */
    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;
    
    /**
     * Indicar la dosis
     */
    @Column(columnDefinition = "TEXT")
    private String dosis;
    
    /**
     * Indicaciones
     */
    @Column(columnDefinition = "TEXT")
    private String indicaciones;
    
    /**
     * Fecha y hora de la creacion de la receta
     */
    @Column(name = "fecha_hora", nullable = false, updatable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    
}
