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
     * Id serializable.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Nombre Medicamento.
     */    
    @Column(name = "nombre")
    private String nombre;

    /**
     * Descripcion del Medicamento.
     */ 
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * Presentacion del Medicamento.
     */ 
    @Column(name = "presentacion")
    private String presentacion;

    /**
     * Fecha Compra de Medicamento.
     */ 
    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    /**
    * Fecha Vencimiento del Medicamento.
    */ 
    @Column(name = "fecha_vence")
    private LocalDate fechaVence;

    /**
     * Fecha Creacion Registro del Medicamento.
     */ 
    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro;

    /**
     * Fecha Modificacion del Registro de Medicamento.
     */     
    @Column(name = "fecha_modificacion_registro")
    private LocalDateTime fechaModificacionRegistro;
}
