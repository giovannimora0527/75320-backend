package com.uniminuto.clinica.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EspecializacionRq {

    private Integer Id;

    @NotNull(message = "El campo nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "El campo codigoEspecializacion es obligatorio")
    private String codigoEspecializacion;
}
