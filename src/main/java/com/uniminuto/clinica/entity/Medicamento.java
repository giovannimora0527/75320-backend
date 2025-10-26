package com.uniminuto.clinica.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Data
@Entity
@Table(name = "medicamento")
public class Medicamento implements Serializable {
    /**
     * serializable
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * nombre
     */
    @Column(name = "nombre")
    private String nombre;
    /**
     * descripcion
     */
    @Column(name = "descripcion")
    private String descripcion;
    /**
     * presentacion
     */
    @Column(name = "presentacion")
    private String presentacion;
    /**
     * fecha_compra
     */
    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;
    /**
     * fecha_venta
     */
    @Column(name = "fecha_vence")
    private LocalDate fechaVence;
    /**
     * fecha_creacion_registro
     */
    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro;
    /**
     * fecha_modificacion_registro
     */
    @Column(name = "fecha_modificacion_registro")
    private LocalDateTime fechaModificacionRegistro;
}
