package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaRq {
    private Integer pacienteId;
    private Long medicoId;
    private LocalDateTime fechaHora;
    private String estado;
    private String motivo;
}
