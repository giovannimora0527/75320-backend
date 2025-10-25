package com.uniminuto.clinica.model;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Se utiliza como objeto de entrada en los controladores REST.
 */
/**
 * @author Anderson
 */

@Data
public class CitaRq {
    /**
     * Otros campos para el objeto de entrada
     */
    private Integer pacienteId;

    private Long medicoId;

    private LocalDateTime fechaHora;

    private String estado;

    private String motivo; 
}
