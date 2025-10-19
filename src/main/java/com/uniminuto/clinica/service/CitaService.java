package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;

public interface CitaService {
    RespuestaRs guardarCita(CitaRq citaRq);
    
    List<Cita> listarCitaPorFechaHora();
}
