package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import lombok.Data;

/**
 * Entidad Paciente.
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
    private Long id;

    /**
     * Usuario asociado al paciente.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /**
     * Tipo de documento del paciente (CC, TI, CE, etc.).
     */
    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String tipoDocumento;

    /**
     * Número de documento del paciente.
     */
    @Column(name = "numero_documento", length = 20, nullable = false, unique = true)
    private String numeroDocumento;

    /**
     * Nombres del paciente.
     */
    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;

    /**
     * Apellidos del paciente.
     */
    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    /**
     * Fecha de nacimiento del paciente.
     */
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    /**
     * Género del paciente (M/F/O).
     */
    @Column(name = "genero", length = 1)
    private String genero;

    /**
     * Teléfono de contacto del paciente.
     */
    @Column(name = "telefono", length = 20)
    private String telefono;

    /**
     * Dirección de residencia del paciente.
     */
    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;
}

