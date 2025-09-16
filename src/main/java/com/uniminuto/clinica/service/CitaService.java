package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author Andre
 */


public interface CitaService {
        List<Cita> listarCita();
        
        // servicio para guardar.
        RespuestaRs guardarCita(CitaRq cita) throws BadRequestException;         
}
