package com.uniminuto.clinica.model;

import lombok.Data;
import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;

/**
 * Request para autenticación de usuario.
 */
@Data
public class AuthenticatorRq implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Nombre de usuario */
    @NotBlank(message = "El username es obligatorio.")
    private String username;

    /** Contraseña del usuario */
    @NotBlank(message = "El password es obligatorio.")
    private String password;
}
