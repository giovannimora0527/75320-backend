package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import java.util.List;

public interface CitaService {
            /**
     * Obtiene la cita para guardar.
     * @param cita
     * @return Lista de citas ordenadas.
     */
    Cita guardarCita(Cita cita);
    
        /**
     * Obtiene una lista de todas las citas ordenadas por fecha y hora descendente.
     * @return Lista de citas ordenadas.
     */
    List<Cita> obtenerCitasOrdenadasPorFechaDesc();
}