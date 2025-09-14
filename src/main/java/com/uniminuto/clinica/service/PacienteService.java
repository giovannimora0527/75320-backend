package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
<<<<<<< HEAD

/**
 *
 * @author Andre
 */
public interface PacienteService {
    List<Paciente> listarPacientes();
    Paciente buscarPorNumeroDocumento(String numeroDocumento);
    
=======
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
      List<Paciente> encontrarTodosLosPacientes();
      Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
}
