package com.uniminuto.clinica.model;

import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 
 * @author Alkri
 */

@Data
public class CitaRq {
    

    /**
     * Id del paciente .
     */
    private Integer pacienteId;

    /**
     * Id del médico .
     */
    private Long medicoId;

    /**
     * Fecha y hora de la cita.
     */
    private LocalDateTime fechaHora;

    /**
     * Estado de la cita (Ej: Pendiente, Confirmada, Cancelada).
     */
    private String estado;

    /**
     * Motivo de la cita.
     */
    private String motivo;
}
