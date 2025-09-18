/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.model;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import lombok.Data;

@Data

/**
 *
 * @author alkri
 */
public class RecetaRq {

    /**
     * Id del medicamento.
     */
    private Integer id;
    private Integer citaId;
    private Integer medicamentoId;
    /**
     * Nombre.
     */

    private String dosis;

    private String indicaciones;

}
