
package com.uniminuto.clinica.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Request para recuperación de contraseña.
 */
@Data
public class RecuperarPasswordRq implements Serializable {
    
    @NotBlank(message = "El Correo es obligatorio.")
    private String email;
}