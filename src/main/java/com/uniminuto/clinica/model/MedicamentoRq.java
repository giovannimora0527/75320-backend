package com.uniminuto.clinica.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicamentoRq {
    /**
     * Id del medicamento.
     */
    private Integer id;
    /**
     * Nombre.
     */
    private String nombre;

    private String descripcion;

    private String presentacion;

    private LocalDate fechaCmpra;

    private LocalDate fechaVence;
}
