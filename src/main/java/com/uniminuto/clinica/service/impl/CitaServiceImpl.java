package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;

import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;

import com.uniminuto.clinica.service.CitaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImpl implements CitaService {

    // Dependencias a los repositorios, declaradas como 'final' para garantizar que se inicialicen
    // a través del constructor y que no cambien.
    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    // Constructor que recibe los repositorios. Spring se encarga de inyectar
    // automáticamente estas dependencias (Inyección por Constructor).
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
        
        // Validar médico: se busca en el repositorio si el médico asociado a la cita existe.
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
            
            // Se utiliza findById y orElseThrow para lanzar una excepción si no se encuentra.
            paciente = pacienteRepository.findById(cita.getPaciente().getId())
                    .orElseThrow(() -> new BadRequestException("Paciente no encontrado"));
        } catch (BadRequestException ex) {
            
            // El bloque catch captura la excepción y la registra.
            // Esto permite que el flujo continúe, aunque el log es un enfoque simple.
            Logger.getLogger(CitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Asigna las entidades completas de Medico y Paciente a la Cita.
        // Esto es crucial para que Hibernate pueda persistir las relaciones correctamente.
        cita.setMedico(medico);
        cita.setPaciente(paciente);

        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> obtenerCitasOrdenadasPorFechaDesc() {
        
        // Delega la lógica de la consulta al repositorio.
        // Spring Data JPA interpreta automáticamente el nombre de este método
        // para generar la consulta SQL apropiada.
        return citaRepository.findAllByOrderByFechaHoraDesc();
    }
}