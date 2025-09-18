package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface PacienteService {
    
    //método diseñado para recuperar una lista de todos los pacientes que existen en la base de datos. 
      List<Paciente> encontrarTodosLosPacientes();
      
    //método utilizado para buscar un paciente específico utilizando su número de documento como identificador. 
      Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;
      
    //método diseñado para recuperar una lista de todos los pacientes qpor la Fecha de Nacimiento. 
      List<Paciente> encontrarTodosLosPacientesXFechaNacimiento();
}
