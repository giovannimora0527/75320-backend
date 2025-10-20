package com.uniminuto.clinica.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data

public class EspecializacionRq {
    private Long id;

    @NotBlank(message = "El nombre de la especialización es obligatorio")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String descripcion;

    @NotBlank(message = "El código de especialización es obligatorio")
    private String codigoEspecializacion;


}
