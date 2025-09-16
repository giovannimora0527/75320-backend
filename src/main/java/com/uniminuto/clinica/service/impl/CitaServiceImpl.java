package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.CitaService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public CitaServiceImpl(
            CitaRepository citaRepository,
            MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository) {
        this.citaRepository = citaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Cita guardarCita(Cita cita) {
        // Validar médico
        Medico medico = null;
        try {
            medico = medicoRepository.findById(cita.getMedico().getId())
                    .orElseThrow(() -> new BadRequestException("Médico no encontrado"));
        } catch (BadRequestException ex) {
            Logger.getLogger(CitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Validar paciente
        Paciente paciente = null;
        try {
            paciente = pacienteRepository.findById(cita.getPaciente().getId())
                    .orElseThrow(() -> new BadRequestException("Paciente no encontrado"));
        } catch (BadRequestException ex) {
            Logger.getLogger(CitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        cita.setMedico(medico);
        cita.setPaciente(paciente);

        return citaRepository.save(cita);
    }
}