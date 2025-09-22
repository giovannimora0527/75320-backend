/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.model;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Oskr
 */
@Data
public class CitaRq {
    
    private LocalDateTime fechaHora;
    private Long pacienteId;
    private Long medicoId;
    private String estado;
    private String motivo;
    
}
