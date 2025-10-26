package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;



public interface CitaService {
    /**
     * Servicio para listar cita
     */
    List<Cita> listarCita();
    /**
     * Servicio para guardar cita
     */
    RespuestaRs guardarCita(CitaRq cita) throws BadRequestException;
    /**
     * Servicio para listar por fecha hora
     */
    List<Cita>ListarCitasOrdenadas();
}
