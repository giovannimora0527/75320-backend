/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Alkri
 */
@Data
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
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
    private long id;
    /**
     * Username.
     */
    @Column(name = "usuario_id")
    private int usuarioId;
    /**
     * Tipo de documento.
     */
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    /**
     * Numero de documento.
     */
    @Column(name = "numero_documento")
    private String numeroDocumento;    
    /**
     * Nombres.
     */
    @Column(name = "nombres")
    private String nombres;
    /**
     * Apellidos.
     */
    @Column(name = "apellidos")
    private String apellidos;
    /**
     * Fecha de nacimiento.
     */
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    /**
     * Genero.
     */
    @Column(name = "genero")
    private char genero;
    /**
     * Telefono.
     */
    @Column(name = "telefono")
    private String telefono;
    /**
     * Direccion.
     */
    @Column(name = "direccion")
    private String direccion;
}

