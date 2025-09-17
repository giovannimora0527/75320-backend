package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;

public interface CitaService {
    RespuestaRs guardarCita(CitaRq citaRq);
}
