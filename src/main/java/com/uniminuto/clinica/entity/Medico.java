package com.uniminuto.clinica.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import lombok.Data;



@Data
@Entity
@Table(name = "medico")
public class Medico implements Serializable {
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
    private Long id;
    /**
     * tipo_documento
     */
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    /**
     * número_documento
     */
    @Column(name = "numero_documento")
    private String numeroDocumento;
    /**
     * nombres
     */
    @Column(name = "nombres")
    private String nombres;
    /**
     * apellidos
     */
    @Column(name = "apellidos")
    private String apellidos;
    /**
     * teléfono
     */
    @Column(name = "telefono")
    private String telefono;
    /**
     * registro_profesional
     */
    @Column(name = "registro_profesional")
    private String registroProfesional;
    /**
     * especializacion_id
     */
    @ManyToOne
    @JoinColumn(name = "especializacion_id")
    private Especializacion especializacion;
}
