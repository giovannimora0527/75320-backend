package com.uniminuto.clinica.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PacienteRq {

    private Integer id;

    @NotNull(message = "El campo usuario_id es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "El campo tipoDocumento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El campo numero_documento es obligatorio")
    private String numeroDocumento;

    @NotBlank(message = "El campo nombres es obligatorio")
    private String nombres;

    @NotBlank(message = "El campo apellidos es obligatorio")
    private String apellidos;

    @NotBlank(message = "El campo fechaNacimiento es obligatorio")
    private LocalDate fechaNacimiento;

    private String genero;

    private String telefono;

    private String direccion;

}