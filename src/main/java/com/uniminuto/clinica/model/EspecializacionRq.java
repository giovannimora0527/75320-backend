package com.uniminuto.clinica.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class EspecializacionRq {
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;
}
