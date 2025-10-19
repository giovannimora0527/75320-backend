package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
      List<Paciente> encontrarTodosLosPacientes();
      Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;
      RespuestaRs guardarPaciente(PacienteRq pacienteRq) throws BadRequestException;
      RespuestaRs eliminarPaciente(Integer id) throws BadRequestException;
      RespuestaRs actualizarPaciente(Integer id, PacienteRq pacienteRq) throws BadRequestException;
}
