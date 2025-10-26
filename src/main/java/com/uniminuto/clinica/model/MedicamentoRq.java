package com.uniminuto.clinica.model;

import lombok.Data;
import java.time.LocalDate;



@Data
public class MedicamentoRq {
    /**
     * Otros campos para el objeto de entrada
     */
    private Integer id;

    private String nombre;

    private String descripcion;

    private String presentacion;

    private LocalDate fechaCompra;

    private LocalDate fechaVence;
}
