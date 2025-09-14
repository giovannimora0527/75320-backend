package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author nicolas
 */
public interface PacienteService {
    /**
     *Lista todos los usuarios de la bd.
     * @return Lista de usuarios.
    */   
    List<Paciente> encontrarTodosLosPacientes();
    
    /**
     * Buscamos un usuario dado su numero_documento.
     * @param numero_documento numero_documento a buscar.
     * @return Paciente que cumpla con el criterio.
     */
    Paciente encontrarPacientePorNumero_Documento(String numero_documento) throws BadRequestException;
}
