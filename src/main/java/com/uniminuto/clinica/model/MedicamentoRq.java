package com.uniminuto.clinica.model;

import java.time.LocalDate;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MedicamentoRq {
    private String nombre;
    private String descripcion;
    private String presentacion;
    private LocalDate fechaCompra;
    private LocalDate fechaVence;
    private Integer cantidad;
}