package com.uniminuto.clinica.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de solicitud para recuperación de contraseña.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecuperarPasswordRq {
    
    /**
     * Nombre de usuario para recuperar la contraseña.
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;
}

