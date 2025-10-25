package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;


public interface CitaService {
    
    List<Cita> listarCitaPorFechaHora();
    RespuestaRs guardarCita(CitaRq citaRq);
    RespuestaRs eliminarCita(Integer id) throws BadRequestException;
    RespuestaRs actualizarCita(Integer id, CitaRq citaRq) throws BadRequestException;

}
