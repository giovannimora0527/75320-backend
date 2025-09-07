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
 * Entidad Paciente.
 * Representa los datos de los pacientes registrados en el sistema.
 * Basado en la tabla paciente.
 * 
 * @author lmora
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
     * Identificador único del paciente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Relación con el usuario.
     */
    @Column(name = "usuario_id", unique = true)
    private Integer usuarioId;

    /**
     * Tipo de documento (CC, TI, etc.).
     */
    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;

    /**
     * Número de documento único.
     */
    @Column(name = "numero_documento", nullable = false, unique = true, length = 20)
    private String numeroDocumento;

    /**
     * Nombres del paciente.
     */
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    /**
     * Apellidos del paciente.
     */
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    /**
     * Fecha de nacimiento.
     */
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    /**
     * Género (M, F, O).
     */
    @Column(name = "genero", length = 1)
    private String genero;

    /**
     * Teléfono de contacto.
     */
    @Column(name = "telefono", length = 20)
    private String telefono;

    /**
     * Dirección del paciente.
     */
    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;
}
