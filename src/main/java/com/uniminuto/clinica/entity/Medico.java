package com.uniminuto.clinica.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

/**
 * Entidad que representa a un médico en el sistema.
 * 
 * Contiene información personal, profesional y su especialización.
 * 
 * @author lmora
 */
@Data
@Entity
@Table(name = "medico")
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del médico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Tipo de documento de identificación (CC, CE, TI, etc.).
     */
    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String tipoDocumento;

    /**
     * Número de documento del médico.
     */
    @Column(name = "numero_documento", length = 20, nullable = false, unique = true)
    private String documento;

    /**
     * Nombres del médico.
     */
    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;

    /**
     * Apellidos del médico.
     */
    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    /**
     * Teléfono de contacto del médico.
     */
    @Column(name = "telefono", length = 20)
    private String telefono;

    /**
     * Número de registro profesional del médico.
     */
    @Column(name = "registro_profesional", length = 50, nullable = false, unique = true)
    private String registroProfesional;

    /**
     * Especialización médica asociada.
     */
    @ManyToOne
    @JoinColumn(name = "especializacion_id", nullable = true)
    private Especializacion especializacion;
}
