package com.uniminuto.clinica.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCompra;

    /**
     * Fecha de vencimiento del medicamento.
     */
    @Column(name = "fecha_vence", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVence;

    /**
     * Fecha y hora de creación del registro.
     */
    @Column(name = "fecha_creacion_registro", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacionRegistro;

    /**
     * Fecha y hora de última modificación del registro.
     */
    @Column(name = "fecha_modificacion_registro")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaModificacionRegistro;
}
