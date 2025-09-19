/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con citas médicas.
 *
 * @author anago
 */
public interface CitaService {

    /**
     * Crea una nueva cita médica.
     *
     * @param cita Objeto Cita a crear
     * @return Cita creada y guardada
     */
    Cita crearCita(Cita cita);

    /**
     * Obtiene todas las citas ordenadas por fecha y hora descendente.
     *
     * @return Lista de citas ordenadas de más recientes a más antiguas
     */
    List<Cita> getAllCitasOrdenadas();

    /**
     * Obtiene citas por ID de paciente.
     *
     * @param pacienteId ID del paciente
     * @return Lista de citas del paciente
     */
    List<Cita> getCitasPorPaciente(Long pacienteId);

    /**
     * Obtiene citas por ID de médico.
     *
     * @param medicoId ID del médico
     * @return Lista de citas del médico
     */
    List<Cita> getCitasPorMedico(Long medicoId);

    /**
     * Verifica disponibilidad de médico en una fecha y hora específica.
     *
     * @param medicoId ID del médico
     * @param fechaHora Fecha y hora a verificar
     * @return true si está disponible, false si ya tiene cita
     */
    boolean verificarDisponibilidadMedico(Long medicoId, LocalDateTime fechaHora);

    public List<Cita> listarCitasRecientes();
}