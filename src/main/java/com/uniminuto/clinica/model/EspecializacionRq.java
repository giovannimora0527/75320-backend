package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Request para crear o actualizar una especialización médica.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecializacionRq implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador de la especialización (usado para actualizaciones) */
    private Integer id;

    /** Nombre de la especialización médica */
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    /** Descripción de la especialización */
    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;

    /** Código único que identifica la especialización */
    @NotBlank(message = "El código de especialidad es obligatorio.")
    private String codigoEspecialidad;
}
