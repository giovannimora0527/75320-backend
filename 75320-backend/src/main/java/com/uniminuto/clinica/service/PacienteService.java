package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;

import java.util.List;

/**
 * Servicio para la gestión de pacientes.
 * Define las operaciones CRUD y consultas específicas.
 */
public interface PacienteService {

    /**
     * Lista todos los pacientes registrados.
     *
     * @return lista de pacientes.
     */
    List<Paciente> encontrarTodosLosPacientes();

    /**
     * Busca un paciente por su número de documento.
     *
     * @param documento número de documento.
     * @return paciente encontrado.
     * @throws BadRequestException si el paciente no existe.
     */
    Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;

    /**
     * Guarda un nuevo paciente.
     *
     * @param pacienteRq datos del paciente.
     * @return respuesta del servicio.
     * @throws BadRequestException si los datos son inválidos.
     */
    RespuestaRs guardarPaciente(PacienteRq pacienteRq) throws BadRequestException;

    /**
     * Elimina un paciente por su ID.
     *
     * @param id identificador del paciente.
     * @return respuesta del servicio.
     * @throws BadRequestException si el paciente no existe.
     */
    RespuestaRs eliminarPaciente(Integer id) throws BadRequestException;

    /**
     * Actualiza la información de un paciente existente.
     *
     * @param id identificador del paciente.
     * @param pacienteRq datos actualizados.
     * @return respuesta del servicio.
     * @throws BadRequestException si el paciente no existe o los datos son inválidos.
     */
    RespuestaRs actualizarPaciente(Integer id, PacienteRq pacienteRq) throws BadRequestException;

    /**
     * Lista los pacientes ordenados por fecha de nacimiento.
     *
     * @param ascendente si es true, los ordena ascendentemente; si es false, descendentemente.
     * @return lista de pacientes ordenada.
     */
    List<Paciente> listarOrdenadoPorFechaNacimiento(boolean ascendente);

    /**
     * Lista los pacientes ordenados por edad (fecha de nacimiento ascendente).
     *
     * @return lista de pacientes ordenada por edad.
     */
    List<Paciente> listarPacientesPorEdad();
}
