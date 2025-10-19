
package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaRq {
    
    private Integer citaId;
    private Integer medicamentoId;
    private String dosis;
    private String indicaciones;
    private LocalDateTime fechaHora;
    
}
