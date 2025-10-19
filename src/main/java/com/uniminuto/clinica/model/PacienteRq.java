package com.uniminuto.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Request DTO para crear/actualizar pacientes.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteRq {
    private Integer id;
    private Integer usuarioId;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String genero;
    private String telefono;
    private String direccion;
}
