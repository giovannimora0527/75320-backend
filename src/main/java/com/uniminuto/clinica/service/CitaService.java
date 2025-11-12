package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;

import java.util.List;

/**
 * Servicio que define las operaciones para la gestión de citas médicas.
 */
public interface CitaService {

    /**
     * Lista todas las citas ordenadas por fecha y hora.
     *
     * @return Lista de citas.
     */
    List<Cita> listarCitas();

    /**
     * Guarda una nueva cita en el sistema.
     *
     * @param citaRq Datos de la cita a registrar.
     * @return Respuesta con el estado de la operación.
     * @throws BadRequestException Si los datos son inválidos.
     */
    RespuestaRs guardarCita(CitaRq citaRq) throws BadRequestException;

    /**
     * Actualiza una cita existente.
     *
     * @param id      Identificador de la cita.
     * @param citaRq  Datos actualizados.
     * @return Respuesta con el resultado de la actualización.
     * @throws BadRequestException Si el ID no existe o los datos son inválidos.
     */
    RespuestaRs actualizarCita(Integer id, CitaRq citaRq) throws BadRequestException;

    /**
     * Elimina una cita del sistema.
     *
     * @param id Identificador de la cita a eliminar.
     * @return Respuesta con el resultado de la eliminación.
     * @throws BadRequestException Si la cita no existe.
     */
    RespuestaRs eliminarCita(Integer id) throws BadRequestException;

    /**
     * Lista las citas asociadas a un paciente.
     *
     * @param pacienteId ID del paciente.
     * @return Lista de citas del paciente.
     * @throws BadRequestException Si el paciente no existe.
     */
    List<Cita> listarCitasPorPaciente(Integer pacienteId) throws BadRequestException;
}
