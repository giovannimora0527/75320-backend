package com.uniminuto.clinica.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entidad que representa a un paciente en el sistema clínico.
 * Contiene la información personal y de contacto del paciente.
 * 
 * @author 
 */
@Data
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del paciente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Identificador del usuario asociado (si aplica).
     */
    @Column(name = "usuario_id")
    private Integer usuarioId;

    /**
     * Tipo de documento del paciente (CC, CE, TI, etc.).
     */
    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String tipoDocumento;

    /**
     * Número de documento de identificación.
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
     * Género del paciente (Masculino, Femenino, Otro, etc.).
     */
    @Column(name = "genero", length = 20)
    private String genero;

    /**
     * Número de teléfono de contacto.
     */
    @Column(name = "telefono", length = 20)
    private String telefono;

    /**
     * Dirección de residencia del paciente.
     */
    @Column(name = "direccion", length = 200)
    private String direccion;
}
