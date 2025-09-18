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
      
      /**
     *
     * @author JulianLopez
     *
     *  Nuevo método: listar pacientes en orden ascendente por fecha de nacimiento
     * @return 


     */
       List<Paciente> encontrarPacientesOrdenadosPorFechaNacimientoAsc();
      


}
