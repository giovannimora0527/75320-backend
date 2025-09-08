package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author Julian
 */
public interface PacienteService {

    List<Paciente> encontrarTodosLosPacientes();

    Paciente encontrarPacientePorDocumento(String numeroDocumento) throws BadRequestException;
}
