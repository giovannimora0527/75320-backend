/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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