package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
      List<Paciente> encontrarTodosLosPacientes();
      Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;
      // metodo para listar por feche de nacimiento
      List<Paciente>listarPacientesPorFechaNacimiento();
}