package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private LocalDate fechaCompra;

    private LocalDate fechaVence;
}
