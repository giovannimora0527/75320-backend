package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
* Servicio de paciente
*/
/**
* @author Anderson
*/

public interface PacienteService {
    /**
    * Servicio para listar los pacientes
    */
    List<Paciente> encontrarTodosLosPacientes();
    /**
    * Servicio para buscar paciente por numero de documento
    */
    Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;
    /**
    * Servicio para listar los pacientes por feche de nacimiento
    */
    List<Paciente>listarPacientesPorFechaNacimiento();
}