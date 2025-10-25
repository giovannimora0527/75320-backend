package com.uniminuto.clinica.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Andre
 */

@Data

public class EspecializacionRq {
    private Long id;

    @NotBlank(message = "El nombre de la especializacion es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotBlank(message = "El codigo es obligatorio")
    private String codigoEspecializacion;


    
}