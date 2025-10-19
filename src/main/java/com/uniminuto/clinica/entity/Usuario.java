package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author lmora
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
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
     * Username.
     */
    @Column(name = "username")
    private String username;
    /**
     * Password.
     */
    @Column(name = "password_hash")
    private String password;
    /**
     * Rol.
     */
    @Column(name = "rol")
    private String rol;    
    /**
     * Fecha Creacion.
     */
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    /**
     * Activo.
     */
    @Column(name = "activo")
    private boolean activo;
    
}
