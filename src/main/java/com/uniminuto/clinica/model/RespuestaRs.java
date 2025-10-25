package com.uniminuto.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaRs {
    private String message;
    private boolean success;
    private int status = 200;
    private Object data; // ✅ nuevo campo para enviar datos (ej: una receta)
}
