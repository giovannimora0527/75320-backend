package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
<<<<<<< HEAD
 * @author lmora
=======
 * @author Julian
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
 */
public interface PacienteService {

    List<Paciente> encontrarTodosLosPacientes();

<<<<<<< HEAD
    Paciente buscarPacientePorDocumento(String documento) throws BadRequestException;

    /**
     *
     * @author JulianLopez
     *
     * Nuevo método: listar pacientes en orden ascendente por fecha de
     * nacimiento
     */
    List<Paciente> encontrarPacientesOrdenadosPorFechaNacimientoAsc();

=======
    Paciente encontrarPacientePorDocumento(String numeroDocumento) throws BadRequestException;
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
}
