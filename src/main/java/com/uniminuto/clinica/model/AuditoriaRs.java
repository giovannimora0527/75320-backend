package com.uniminuto.clinica.model;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Modelo de respuesta para un registro de auditoría.
 * Representa un evento de auditoría (login o recuperación de contraseña).
 * 
 * @author Sistema
 */
@Data
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
     * ID del usuario (si el evento fue exitoso).
     */
    private Long usuarioId;
    
    /**
     * Email del usuario (solo para recuperación de contraseña).
     */
    private String emailUsuario;
    
    /**
     * Tipo de evento: "LOGIN" o "RECUPERACION".
     */
    private String tipoEvento;
}

