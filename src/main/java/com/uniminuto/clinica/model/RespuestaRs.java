package com.uniminuto.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * Clase de respuesta genérica para las operaciones del API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespuestaRs {

    /** Mensaje descriptivo de la respuesta */
    private String mensaje;

    /** Indica si la operación fue exitosa */
    private boolean success;

    /** Código de estado HTTP o interno */
    private int status;

    /** Datos opcionales devueltos por la operación */
    private Object data;
}
