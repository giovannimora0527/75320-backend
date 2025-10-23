package com.uniminuto.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   // ✅ genera un constructor con todos los argumentos
@NoArgsConstructor    // ✅ genera un constructor vacío
public class RespuestaRs {
    private String message;
    private boolean success;
    private int status = 200; // opcional

    // Puedes agregar métodos auxiliares si quieres
}
