package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.CitaService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Cita guardarCita(Cita cita) {
        if (cita.getMedico() == null || cita.getMedico().getId() == null ||
            !medicoRepository.existsById(cita.getMedico().getId())) {
            throw new IllegalArgumentException("El médico no existe");
        }
        if (cita.getPaciente() == null || cita.getPaciente().getId() == null ||
            !pacienteRepository.existsById(cita.getPaciente().getId())) {
            throw new IllegalArgumentException("El paciente no existe");
        }
        if (cita.getFechaHora() == null) {
            throw new IllegalArgumentException("La fecha y hora de la cita es obligatoria");
        }
        return citaRepository.save(cita);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listarCitasRecientes() {
        return citaRepository.findAllByOrderByFechaHoraDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listarCitasPorRango(LocalDateTime desde, LocalDateTime hasta) {
        return citaRepository.findByFechaHoraBetweenOrderByFechaHoraDesc(desde, hasta);
    }
}










