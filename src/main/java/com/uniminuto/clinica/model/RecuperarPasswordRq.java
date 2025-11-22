package com.uniminuto.clinica.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class RecuperarPasswordRq {
    @NotBlank(message = "El username es obligatorio.")
    private String username;
}