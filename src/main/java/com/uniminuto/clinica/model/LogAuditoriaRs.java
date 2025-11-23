package com.uniminuto.clinica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Modelo de respuesta para un registro de log de auditoría.
 * Representa un evento de auditoría unificado (login o recuperación de contraseña).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAuditoriaRs {

    /** Identificador único del log */
    private Long id;

    /** Fecha y hora de la transacción */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaHora;

    /** Nombre de usuario relacionado con el evento */
    private String usuario;

    /** Tipo de evento registrado (LOGIN_EXITOSO, LOGIN_FALLIDO, RECUPERACION_PASSWORD, etc.) */
    private String tipoEvento;

    /** Descripción detallada del evento */
    private String descripcion;

    /** Dirección IP desde donde se originó la acción */
    private String direccionIp;

    /** Información adicional en formato JSON (opcional) */
    private Object metadata;
}

