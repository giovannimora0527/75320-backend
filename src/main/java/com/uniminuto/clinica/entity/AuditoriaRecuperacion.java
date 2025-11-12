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
 * Entidad para registrar los intentos de recuperación de contraseña.
 * Permite auditoría de seguridad para rastrear intentos de recuperación.
 * 
 * @author Sistema
 */
@Data
@Entity
@Table(name = "auditoria_recuperacion")
public class AuditoriaRecuperacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Nombre de usuario ingresado en el intento de recuperación.
     */
    @Column(name = "username_ingresado", nullable = false, length = 50)
    private String usernameIngresado;
    
    /**
     * Fecha y hora de la transacción.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    /**
     * Descripción del resultado (éxito o error).
     */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    /**
     * Indica si el intento fue exitoso (usuario válido) o fallido (usuario inválido).
     */
    @Column(name = "exitoso", nullable = false)
    private boolean exitoso;
    
    /**
     * Email del usuario (solo si el intento fue exitoso).
     */
    @Column(name = "email_usuario", length = 100)
    private String emailUsuario;
    
    /**
     * IP desde donde se realizó la solicitud (opcional, para mayor seguridad).
     */
    @Column(name = "ip_origen", length = 45)
    private String ipOrigen;
}

