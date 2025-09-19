package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {

    Cita guardarCita(Cita cita);

    List<Cita> listarCitasRecientes();

    List<Cita> listarCitasPorRango(LocalDateTime desde, LocalDateTime hasta);
}










