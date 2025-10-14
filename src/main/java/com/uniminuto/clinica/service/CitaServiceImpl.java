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
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public Cita crearCita(Cita cita) {
        Paciente paciente = pacienteRepository.findById(cita.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(cita.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setActivo(true);
        return citaRepository.save(cita);
    }

    @Override
    public Cita actualizarCita(Long id, Cita cita) {
        Cita existente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        existente.setFechaCita(cita.getFechaCita());
        existente.setMotivo(cita.getMotivo());
        existente.setPaciente(cita.getPaciente());
        existente.setMedico(cita.getMedico());
        return citaRepository.save(existente);
    }

    @Override
    public void eliminarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        citaRepository.delete(cita);
    }

    @Override
    public Cita activarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setActivo(true);
        return citaRepository.save(cita);
    }

    @Override
    public Cita desactivarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setActivo(false);
        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> listarCitasRecientes() {
        return citaRepository.findAllByOrderByFechaCitaDesc();
    }

    @Override
    public List<Cita> listarCitasActivas() {
        return citaRepository.findByActivoTrue();
    }


}

