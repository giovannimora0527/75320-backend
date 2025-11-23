package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.HistoriaMedica;
import com.uniminuto.clinica.model.HistoriaMedicaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;

import java.util.List;

/**
 * Servicio para la gestión de historias médicas.
 */
public interface HistoriaMedicaService {

    /**
     * Lista todas las historias médicas registradas.
     * @return lista de historias médicas.
     */
    List<HistoriaMedica> listarHistorias();

    /**
     * Obtiene una historia médica por su ID.
     * @param id identificador de la historia médica.
     * @return la historia médica encontrada.
     * @throws BadRequestException si la historia médica no existe.
     */
    HistoriaMedica obtenerPorId(Integer id) throws BadRequestException;

    /**
     * Obtiene las historias médicas de un paciente específico.
     * @param pacienteId identificador del paciente.
     * @return lista de historias médicas del paciente.
     */
    List<HistoriaMedica> listarPorPaciente(Integer pacienteId);

    /**
     * Guarda una nueva historia médica.
     * @param historiaMedicaRq datos de la historia médica.
     * @return respuesta del servicio.
     * @throws BadRequestException si los datos son inválidos.
     */
    RespuestaRs guardarHistoria(HistoriaMedicaRq historiaMedicaRq) throws BadRequestException;

    /**
     * Actualiza una historia médica existente.
     * @param id identificador de la historia médica.
     * @param historiaMedicaRq datos actualizados.
     * @return respuesta del servicio.
     * @throws BadRequestException si la historia médica no existe o los datos son inválidos.
     */
    RespuestaRs actualizarHistoria(Integer id, HistoriaMedicaRq historiaMedicaRq) throws BadRequestException;

    /**
     * Elimina una historia médica por su ID.
     * @param id identificador de la historia médica.
     * @return respuesta del servicio.
     * @throws BadRequestException si la historia médica no existe.
     */
    RespuestaRs eliminarHistoria(Integer id) throws BadRequestException;
}

