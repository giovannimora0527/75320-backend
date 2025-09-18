package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julian
 */
@Service
public class PacienteServicempl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente encontrarPacientePorDocumento(String numeroDocumento) throws BadRequestException {
        Optional<Paciente> optUser = this.pacienteRepository
                .findByNumeroDocumento(numeroDocumento);
        if (!optUser.isPresent()) {
            throw new BadRequestException("No se encuentra el paciente.");
        }
        return optUser.get();
    }

}
