/**
 * Modelo de datos para la solicitud de recuperación de contraseña.
 * Representa los datos requeridos para iniciar el proceso de recuperación de credenciales
 * en el sistema clínico.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class RecuperarPasswordRq {

    /**
     * Nombre de usuario del cual se solicita la recuperación de contraseña.
     * Campo obligatorio que identifica de manera única al usuario en el sistema.
     * Se valida que no esté vacío o en blanco.
     */
    @NotBlank(message = "El username es obligatorio.")
    private String username;
}