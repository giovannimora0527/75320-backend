package com.uniminuto.clinica.model;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Andre
 */

@Data
public class CitaRq {


    /**
     * otros campos para el objeto de entrada.
     */
    private Integer pacienteId;

    private Long medicoId;

    private LocalDateTime fechaHora;

    private String estado;

    private String motivo;
    
}
