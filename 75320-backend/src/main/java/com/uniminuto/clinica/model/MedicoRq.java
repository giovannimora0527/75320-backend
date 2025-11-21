package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Request para crear o actualizar un médico.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoRq implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador del médico (solo para actualización) */
    private Integer id;

    /** Tipo de documento del médico */
    @NotBlank(message = "El tipo de documento es obligatorio.")
    private String tipoDocumento;

    /** Número de documento del médico */
    @NotBlank(message = "El número de documento es obligatorio.")
    private String numeroDocumento;

    /** Nombres del médico */
    @NotBlank(message = "Los nombres son obligatorios.")
    private String nombres;

    /** Apellidos del médico */
    @NotBlank(message = "Los apellidos son obligatorios.")
    private String apellidos;

    /** Teléfono de contacto del médico */
    @NotBlank(message = "El teléfono es obligatorio.")
    private String telefono;

    /** Registro profesional del médico */
    @NotBlank(message = "El registro profesional es obligatorio.")
    private String registroProfesional;

    /** ID de la especialización asociada */
    @NotNull(message = "La especialización es obligatoria.")
    private Integer especializacionId;
}
