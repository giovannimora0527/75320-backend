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
 * Entidad para registrar los intentos de inicio de sesión (exitosos y fallidos).
 * Permite auditoría de seguridad para rastrear intentos de login y detectar posibles ataques.
 * 
 * @author Sistema
 */
@Data
@Entity
@Table(name = "auditoria_login")
public class AuditoriaLogin implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Nombre de usuario ingresado en el intento de login.
     */
    @Column(name = "username_ingresado", nullable = false, length = 50)
    private String usernameIngresado;
    
    /**
     * Fecha y hora del intento de login.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    /**
     * Indica si el intento fue exitoso o fallido.
     */
    @Column(name = "exitoso", nullable = false)
    private boolean exitoso;
    
    /**
     * Descripción del resultado (éxito o motivo del fallo).
     */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    /**
     * IP desde donde se realizó el intento de login (opcional, para mayor seguridad).
     */
    @Column(name = "ip_origen", length = 45)
    private String ipOrigen;
    
    /**
     * ID del usuario si el login fue exitoso (null si falló).
     */
    @Column(name = "usuario_id")
    private Long usuarioId;
}

