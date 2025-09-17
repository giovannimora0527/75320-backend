package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
      List<Paciente> encontrarTodosLosPacientes();
      Paciente buscarPacientePorDocumento(String documento) throws ResponseStatusException;
      List<Paciente> listarPacientesPorEdad();
}
