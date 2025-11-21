package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servicio para la gestión de pacientes.
 * Define las operaciones CRUD y consultas específicas.
 */
public interface PacienteService {

    List<Paciente> encontrarTodosLosPacientes();

    Paciente buscarPacientePorDocumento(String documento) throws ResponseStatusException;

    RespuestaRs guardarPaciente(PacienteRq pacienteRq) throws ResponseStatusException;

    RespuestaRs eliminarPaciente(Integer id) throws ResponseStatusException;

    RespuestaRs actualizarPaciente(Integer id, PacienteRq pacienteRq) throws ResponseStatusException;

    List<Paciente> listarPacientesPorEdad();
}
