package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
      List<Paciente> encontrarTodosLosPacientes();
      Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;
      List<Paciente> encontrarPacientesFNacimientoDesc();
      List<Paciente> buscarPacientePorGenero(String genero);
      Paciente crearPaciente(Paciente p);
      Paciente actualizarPaciente(Integer id, Paciente p) throws BadRequestException;
      void eliminarPaciente(Integer id) throws BadRequestException;
      Optional<Paciente> buscarPorIdPaciente(Integer id);
}
