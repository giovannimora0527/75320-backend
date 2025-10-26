package com.uniminuto.clinica.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacienteRq {
    private Integer id;

    @NotBlank(message = "El id de usuario es obligatorio")
    private Integer usuarioId;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private String fechaNacimiento;

    @NotBlank(message = "El genero es obligatorio")
    private String genero;

    private String telefono;

    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;
}
