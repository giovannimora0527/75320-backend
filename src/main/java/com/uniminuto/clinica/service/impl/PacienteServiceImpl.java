package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andre
 */
@Service
public class PacienteServiceImpl implements PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Override
    
    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }
}
