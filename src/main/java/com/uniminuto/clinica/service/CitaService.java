package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import java.util.List;

public interface CitaService {

    Cita crearCita(Cita cita);

    Cita actualizarCita(Long id, Cita cita);

    void eliminarCita(Long id);

    Cita activarCita(Long id);

    Cita desactivarCita(Long id);

    List<Cita> listarCitasRecientes();

    List<Cita> listarCitasActivas();
}





