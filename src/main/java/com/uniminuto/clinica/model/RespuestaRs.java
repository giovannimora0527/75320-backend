package com.uniminuto.clinica.model;

import lombok.Data;



@Data
public class RespuestaRs {
    /**
     * Otros campos para el objeto de entrada
     */
    private String message;

    private Integer status;
}
