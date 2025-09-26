package com.uniminuto.clinica.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad que representa una receta médica en el sistema.
 * Corresponde a la tabla 'receta' en la base de datos.
 */
@Entity
@Table(name = "receta")
@Data
public class Receta implements Serializable {
    /** Identificador único de la receta */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Identificador de la cita asociada */
    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    /** Identificador del medicamento recetado */
    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    /** Dosis del medicamento */
    @Column(name = "dosis", nullable = false)
    private String dosis;

    /** Indicaciones adicionales para el medicamento */
    @Column(name = "indicaciones")
    private String indicaciones;

    /** Fecha de creación del registro de la receta */
    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro;
}