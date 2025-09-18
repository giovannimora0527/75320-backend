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
    private PacienteRepository pacienteRepository;
    
    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente encontrarPacientePorNombre(String nombres) throws BadRequestException {
        Optional<Paciente> optUser = this.pacienteRepository
                .findByNombres(nombres);
        if (!optUser.isPresent()) {
            throw new BadRequestException("PACIENTE NO ENCONTRADO.");
        }
        
        return optUser.get();
    }
    @Override
    public Paciente buscarPorNumeroDocumento(String numeroDocumento) throws BadRequestException {
        Optional<Paciente> optUser = this.pacienteRepository
                .findByNumeroDocumento(numeroDocumento);
        if (!optUser.isPresent()) {
            throw new BadRequestException("PACIENTE NO ENCONTRADO POR DOCUMENTO.");
        }
        
        return optUser.get();
    }
    
    /**
     * se hace la implementacion del servicio en forma Ascendente
     * @return todos los pacientes ordenados
     * @throws BadRequestException 
     */
       @Override
    public List<Paciente> listarPorFecha()throws BadRequestException{
        
        List<Paciente> pacientes = this.pacienteRepository.findAllByOrderByFechaNacimientoAsc();
        if (pacientes.isEmpty()) {
            throw new BadRequestException("NO HAY REGISTROS");
        }
        return pacientes;
    }
    
}
