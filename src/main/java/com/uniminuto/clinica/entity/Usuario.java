package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entidad de usuario de la base de datos
 */
/**
 * @author Anderson
 */

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
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
     * username
     */
    @Column(name = "username")
    private String username;
    /**
     * password
     */
    @Column(name = "password_hash")
    private String password;
    /**
     * rol
     */
    @Column(name = "rol")
    private String rol;    
    /**
     * fecha_creacion
     */
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    /**
     * activo
     */
    @Column(name = "activo")
    private boolean activo;   
}
