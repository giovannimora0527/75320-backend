package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Request para crear o actualizar un medicamento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoRq implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador del medicamento (usado para actualizaciones) */
    private Integer id;

    /** Nombre del medicamento */
    @NotBlank(message = "El nombre del medicamento es obligatorio.")
    private String nombre;

    /** Descripción del medicamento */
    @NotBlank(message = "La descripción del medicamento es obligatoria.")
    private String descripcion;

    /** Presentación del medicamento (por ejemplo: tabletas, jarabe, etc.) */
    @NotBlank(message = "La presentación del medicamento es obligatoria.")
    private String presentacion;

    /** Fecha de compra */
    @NotNull(message = "La fecha de compra es obligatoria.")
    private LocalDate fechaCompra;

    /** Fecha de vencimiento */
    @NotNull(message = "La fecha de vencimiento es obligatoria.")
    private LocalDate fechaVence;

    /** Cantidad disponible en inventario */
    @NotNull(message = "La cantidad es obligatoria.")
    private Integer cantidad;
}
