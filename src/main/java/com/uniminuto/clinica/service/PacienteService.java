package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;

import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
    List<Paciente> encontrarTodosLosPacientes();
    Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;

    List<Paciente> listarPacientesPorEdad();

    RespuestaRs agregarPaciente(PacienteRq pacienteRq) throws BadRequestException;

    RespuestaRs actualizarPaciente(PacienteRq pacienteRq) throws BadRequestException;

}