package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public Cita crearCita(Cita cita) {
        Paciente paciente = pacienteRepository.findById(cita.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(cita.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        cita.setPaciente(paciente);
        cita.setMedico(medico);

        return citaRepository.save(cita);
    }

    public List<Cita> listarCitasRecientes() {
        return citaRepository.findAllByOrderByFechaCitaDesc();
    }
}




