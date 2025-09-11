/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author Alkri
 */
@Service
public class PacienteServiceImpl implements PacienteService{
    
    /**
     * PacienteRepository.
     */
    @Autowired
    private PacienteRepository PacienteRepository;
    
    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return this.PacienteRepository.findAll();
    }

    @Override
    public Paciente encontrarPacientePorNombre(String nombres) throws BadRequestException {
        Optional<Paciente> optUser = this.PacienteRepository
                .findByNombres(nombres);
        if (!optUser.isPresent()) {
            throw new BadRequestException("PACIENTE NO ENCONTRADO.");
        }
        
        return optUser.get();
    }
    @Override
    public Paciente buscarPorNumeroDocumento(String numeroDocumento) throws BadRequestException {
        Optional<Paciente> optUser = this.PacienteRepository
                .findByNumeroDocumento(numeroDocumento);
        if (!optUser.isPresent()) {
            throw new BadRequestException("PACIENTE NO ENCONTRADO POR DOCUMENTO.");
        }
        
        return optUser.get();
    }
    
}
