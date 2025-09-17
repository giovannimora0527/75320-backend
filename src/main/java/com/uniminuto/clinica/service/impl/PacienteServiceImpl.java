package com.uniminuto.clinica.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPacientePorDocumento(String documento) {
        return pacienteRepository.findByNumeroDocumento(documento)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encuentra el paciente"
                ));
    }

    @Override
    public List<Paciente> listarPacientesPorEdad() {
        return pacienteRepository.findAllByOrderByFechaNacimientoAsc();
    }
}
