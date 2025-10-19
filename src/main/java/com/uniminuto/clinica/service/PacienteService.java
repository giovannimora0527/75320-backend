package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;

import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
    List<Paciente> encontrarTodosLosPacientes();
    Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;

    List<Paciente> listarPacientesPorEdad();

    RespuestaRs guardarPaciente(PacienteRq pacienteRq) throws BadRequestException;

    RespuestaRs actualizarPaciente(PacienteRq pacienteRq) throws BadRequestException;
}