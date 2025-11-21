package com.uniminuto.clinica.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad que representa una receta médica.
 * Cada receta está asociada a una cita y un medicamento.
 * 
 * @author 
 */
@Data
@Entity
@Table(name = "receta")
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la receta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Cita asociada a la receta.
     */
    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    /**
     * Medicamento prescrito.
     */
    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    /**
     * Dosis recomendada para el medicamento.
     */
    @Column(name = "dosis", columnDefinition = "TEXT", nullable = false)
    private String dosis;

    /**
     * Indicaciones adicionales para el uso del medicamento.
     */
    @Column(name = "indicaciones", columnDefinition = "TEXT")
    private String indicaciones;

    /**
     * Fecha y hora de creación de la receta.
     */
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    /**
     * Fecha y hora de la última modificación (si aplica).
     */
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
