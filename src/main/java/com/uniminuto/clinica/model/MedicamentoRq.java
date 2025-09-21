package com.uniminuto.clinica.model;

import lombok.Data;
import java.time.LocalDate;

/**
 * Se utiliza como objeto de entrada en los controladores REST.
 */
/**
 * @author Anderson
 */

@Data
public class MedicamentoRq {
    /**
     * Otros campos para el objeto de entrada
     */
    private Integer id;

    private String nombre;

    private String descripcion;

    private String presentacion;

    private LocalDate fechaCmpra;

    private LocalDate fechaVence;
}