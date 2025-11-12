package com.uniminuto.clinica.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad que representa un medicamento en el sistema.
 */
@Data
@Entity
@Table(name = "medicamento")
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del medicamento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    /**
     * Nombre del medicamento.
     */
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    /**
     * Descripción del medicamento.
     */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /**
     * Presentación del medicamento (tableta, cápsula, jarabe, etc.).
     */
    @Column(name = "presentacion", length = 100)
    private String presentacion;

    /**
     * Cantidad disponible en inventario.
     */
    @Column(name = "cantidad")
    private Integer cantidad;

    /**
     * Fecha en que se adquirió el medicamento.
     */
    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    /**
     * Fecha de vencimiento del medicamento.
     */
    @Column(name = "fecha_vence", nullable = false)
    private LocalDate fechaVence;

    /**
     * Fecha y hora de creación del registro.
     */
    @Column(name = "fecha_creacion_registro", nullable = false)
    private LocalDateTime fechaCreacionRegistro;

    /**
     * Fecha y hora de última modificación del registro.
     */
    @Column(name = "fecha_modificacion_registro")
    private LocalDateTime fechaModificacionRegistro;
}
