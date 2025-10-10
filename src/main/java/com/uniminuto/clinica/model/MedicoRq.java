package com.uniminuto.clinica.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MedicoRq {

    private Long id;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    private String telefono;

    @NotBlank(message = "El registro profesional es obligatorio")
    private String registroProfesional;

    @NotNull(message = "La especialización es obligatoria")
    private Integer especializacionId;
}
