package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;
import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
=======
import org.apache.coyote.BadRequestException;
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
<<<<<<< HEAD
 * @author Andre
 */

=======
 * @author lmora
 */
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
<<<<<<< HEAD
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPorNumeroDocumento(String numeroDocumento) {
        Optional<Paciente> paciente = pacienteRepository.findByNumeroDocumento(numeroDocumento);
        return paciente.orElse(null); // o lanzar excepción si no se encuentra
    }
=======
    private PacienteRepository PacienteRepository;

    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return this.PacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPacientePorDocumento(String documento) throws BadRequestException {
        
        Optional<Paciente> optPaciente = this.PacienteRepository.findByNumeroDocumento(documento);
        if (!optPaciente.isPresent()) {
            throw new BadRequestException("No se encuentra el paciente");
        
        }
        return optPaciente.get();
    }

>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
}
