package com.uniminuto.clinica.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author lmora
 */
@Data
@Entity
@Table(name = "medico")
public class Medico implements Serializable {
    /**
     * Id serializable.
     */    
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del médico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;    

    /**
     * Tipo de documento del médico (por ejemplo, CC, TI, CE).
     */
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    /**
     * Número de documento del médico.
     */
    @Column(name = "numero_documento")
    private String numeroDocumento;

    /**
     * Nombres del médico.
     */
    @Column(name = "nombres")
    private String nombres;

    /**
     * Apellidos del médico.
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * Número de teléfono de contacto del médico.
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Número de registro profesional del médico.
     */
    @Column(name = "registro_profesional")
    private String registroProfesional;
    
    /**
     * Especializacion del medico.
     */
    @ManyToOne
    @JoinColumn(name = "especializacion_id", nullable = true)
    private Especializacion especializacion;
}
