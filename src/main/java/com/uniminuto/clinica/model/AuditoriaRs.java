package com.uniminuto.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Modelo de respuesta para los registros de auditoría.
 * Contiene la información de los intentos de login y recuperación de contraseña.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaRs {
    
    /**
     * ID del registro de auditoría.
     */
    private Long id;
    
    /**
     * Nombre de usuario ingresado.
     */
    private String usernameIngresado;
    
    /**
     * Fecha y hora del evento.
     */
    private LocalDateTime fechaHora;
    
    /**
     * Indica si el intento fue exitoso.
     */
    private Boolean exitoso;
    
    /**
     * Descripción del evento.
     */
    private String descripcion;
    
    /**
     * IP de origen (si está disponible).
     */
    private String ipOrigen;
    
    /**
     * ID del usuario (si el login fue exitoso).
     */
    private Long usuarioId;
    
    /**
     * Email del usuario (solo para recuperación de contraseña).
     */
    private String emailUsuario;
    
    /**
     * Tipo de auditoría: "LOGIN" o "RECUPERACION".
     */
    private String tipo;
}

