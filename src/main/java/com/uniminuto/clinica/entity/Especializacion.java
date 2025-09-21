package com.uniminuto.clinica.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entidad de especializacion de la base de datos
 */
/**
 * @author Anderson
 */

@Data
@Entity
@Table(name = "especializacion")
public class Especializacion implements Serializable {
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
     * codigo_especializacion
     */
    @Column(name = "codigo_especializacion")
    private String codigoEspecializacion;
}