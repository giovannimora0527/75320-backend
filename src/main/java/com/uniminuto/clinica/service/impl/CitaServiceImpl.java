package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.CitaService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<Cita> listarCitasOrdenadas() {
        return this.citaRepository.findAllByOrderByFechaHoraDesc();
    }

    @Override
    public RespuestaRs guardarCita(CitaRq citaRq) throws BadRequestException {
        Optional<Paciente> optPaciente = this.pacienteRepository.findById(citaRq.getPacienteId());
        if (optPaciente.isEmpty()) {
            throw new BadRequestException("El paciente con ID " + citaRq.getPacienteId() + " no existe.");
        }

        Optional<Medico> optMedico = this.medicoRepository.findById(citaRq.getMedicoId());
        if (optMedico.isEmpty()) {
            throw new BadRequestException("El médico con ID " + citaRq.getMedicoId() + " no existe.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaInicioCita = LocalDateTime.parse(citaRq.getFechaHora(), formatter);
        LocalDateTime fechaFinCita = fechaInicioCita.plusMinutes(15);

        List<Cita> citasMedico = this.citaRepository.findByMedicoAndFechaHoraBetween(optMedico.get(), fechaInicioCita, fechaFinCita);
        if (!citasMedico.isEmpty()) {
            throw new BadRequestException("El médico ya tiene una cita programada en este horario.");
        }
        List<Cita> citasPaciente = this.citaRepository.findByPacienteAndFechaHoraBetween(optPaciente.get(), fechaInicioCita, fechaFinCita);
        if (!citasPaciente.isEmpty()) {
            throw new BadRequestException("El paciente ya tiene una cita programada en este horario.");
        }

        Cita citaGuardar = this.convertCitaRqToCitaEntity(citaRq, optPaciente.get(), optMedico.get());
        this.citaRepository.save(citaGuardar);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Cita guardada exitosamente.");
        rta.setStatus(200);
        return rta;
    }

    @Override
    public RespuestaRs actualizarCita(CitaRq citaRq) throws BadRequestException {
        Optional<Cita> optCita = this.citaRepository.findById(citaRq.getId());
        if (optCita.isEmpty()) {
            throw new BadRequestException("La cita con ID " + citaRq.getId() + " no existe.");
        }
        Cita cita = optCita.get();
        if (!citaRq.getPacienteId().equals(cita.getPaciente().getId())) {
            throw new BadRequestException("No se puede cambiar el paciente de la cita.");
        }

        Optional<Medico> optMedico;
        Medico medico = cita.getMedico();
        if (!citaRq.getMedicoId().equals(cita.getMedico().getId())) {
            optMedico = this.medicoRepository.findById(citaRq.getMedicoId());
            if (optMedico.isEmpty()) {
                throw new BadRequestException("El médico con ID " + citaRq.getMedicoId() + " no existe.");
            }
            medico = optMedico.get();
        }

        LocalDateTime fechaCita = LocalDateTime.parse(citaRq.getFechaHora(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m:ss"));
        LocalDateTime fechaActual = LocalDateTime.now();
        if (fechaCita.isBefore(fechaActual)) {
            throw new BadRequestException("No se puede modificar una cita con fecha anterior a la fecha actual.");
        }
        if (citaRq.getFechaHora() != null && !citaRq.getFechaHora().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime nuevaFechaHora = LocalDateTime.parse(citaRq.getFechaHora(), formatter);
            LocalDateTime nuevaFechaFinCita = nuevaFechaHora.plusMinutes(15);

            List<Cita> citasMedico = this.citaRepository.findByMedicoAndFechaHoraBetween(medico, nuevaFechaHora, nuevaFechaFinCita);
            citasMedico.removeIf(c -> c.getId().equals(cita.getId()));
            if (!citasMedico.isEmpty()) {
                throw new BadRequestException("El médico ya tiene una cita programada en este horario.");
            }
            List<Cita> citasPaciente = this.citaRepository.findByPacienteAndFechaHoraBetween(cita.getPaciente(), nuevaFechaHora, nuevaFechaFinCita);
            citasPaciente.removeIf(c -> c.getId().equals(cita.getId()));
            if (!citasPaciente.isEmpty()) {
                throw new BadRequestException("El paciente ya tiene una cita programada en este horario.");
            }
            cita.setFechaHora(nuevaFechaHora);
        }

        if (!medico.getId().equals(cita.getMedico().getId())) {
            cita.setMedico(medico);
        }
        if (citaRq.getEstado() != null && !citaRq.getEstado().isEmpty()) {
            cita.setEstado(citaRq.getEstado());
        }

        if (citaRq.getFechaHora() != null && !citaRq.getFechaHora().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            cita.setFechaHora(LocalDateTime.parse(citaRq.getFechaHora(), formatter));
        }

        this.citaRepository.save(cita);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Cita actualizada exitosamente.");
        rta.setStatus(200);
        return rta;
    }


    private Cita convertCitaRqToCitaEntity(CitaRq citaRq, Paciente paciente, Medico medico) {
        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaHora(LocalDateTime.now());
        cita.setEstado(citaRq.getEstado());
        cita.setMotivo(citaRq.getMotivo());
        return cita;
    }
}
