/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

/**
 *
 * @author Oskr
 */
@Service
public class CitaServiceImpl implements CitaService {
    

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public Cita guardadoCita(Cita cita) {
        // Buscar el paciente en la base
        Paciente paciente = pacienteRepository.findById(cita.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Buscar el médico en la base
        Medico medico = medicoRepository.findById(cita.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        // Reemplazar los objetos incompletos por los que sí tienen datos
        cita.setPaciente(paciente);
        cita.setMedico(medico);

        // Guardar la cita
        return citaRepository.save(cita);
    }
    
    @Override
    public List<Cita> listadodeCitasRecientes() {
        return citaRepository.findAllByOrderByFechaHora();
        }
    
}
