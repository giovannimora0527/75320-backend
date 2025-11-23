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
import lombok.RequiredArgsConstructor;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para la gestión de citas médicas.
 */
@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    /**
     * Lista todas las citas del sistema.
     *
     * @return lista de citas.
     */
    @Override
    public List<Cita> listarCitas() {
        return this.citaRepository.findAllByOrderByFechaHoraDesc();
    }

    /**
     * Crea una nueva cita médica validando disponibilidad de médico y paciente.
     *
     * @param citaRq datos de la cita.
     * @return respuesta del proceso.
     * @throws BadRequestException si el médico o paciente no existen, o si hay conflicto de horario.
     */
    @Override
    @Transactional
    public RespuestaRs guardarCita(CitaRq citaRq) throws BadRequestException {
        Optional<Paciente> optPaciente = this.pacienteRepository.findById(citaRq.getPacienteId());
        if (optPaciente.isEmpty()) {
            throw new BadRequestException("El paciente con ID " + citaRq.getPacienteId() + " no existe.");
        }

        Optional<Medico> optMedico = this.medicoRepository.findById(citaRq.getMedicoId());
        if (optMedico.isEmpty()) {
            throw new BadRequestException("El médico con ID " + citaRq.getMedicoId() + " no existe.");
        }

        LocalDateTime fechaInicio = citaRq.getFechaHora();
        LocalDateTime fechaFin = fechaInicio.plusMinutes(20);

        // Validar disponibilidad del médico
        List<Cita> citasDelMedico = this.citaRepository
                .findByMedicoAndFechaHoraBetween(optMedico.get(), fechaInicio, fechaFin);
        if (!citasDelMedico.isEmpty()) {
            throw new BadRequestException("El médico ya tiene una cita programada en ese horario.");
        }

        // Validar disponibilidad del paciente
        List<Cita> citasDelPaciente = this.citaRepository
                .findByPacienteAndFechaHoraBetween(optPaciente.get(), fechaInicio, fechaFin);
        if (!citasDelPaciente.isEmpty()) {
            throw new BadRequestException("El paciente ya tiene una cita programada en ese horario.");
        }

        // Crear y guardar la nueva cita
        Cita cita = new Cita();
        cita.setPaciente(optPaciente.get());
        cita.setMedico(optMedico.get());
        cita.setFechaHora(fechaInicio);
        cita.setEstado(citaRq.getEstado());
        cita.setMotivo(citaRq.getMotivo());

        this.citaRepository.save(cita);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Cita creada correctamente");
        respuesta.setSuccess(true);
        respuesta.setData(cita);
        return respuesta;
    }

    /**
     * Actualiza una cita existente.
     *
     * @param id      identificador de la cita.
     * @param citaRq  datos nuevos de la cita.
     * @return respuesta del servicio.
     * @throws BadRequestException si la cita no existe.
     */
    @Override
    @Transactional
    public RespuestaRs actualizarCita(Integer id, CitaRq citaRq) throws BadRequestException {
        Optional<Cita> citaOpt = citaRepository.findById(id);
        if (citaOpt.isEmpty()) {
            throw new BadRequestException("No existe la cita con ID " + id);
        }

        Cita cita = citaOpt.get();
        cita.setFechaHora(citaRq.getFechaHora());
        cita.setEstado(citaRq.getEstado());
        cita.setMotivo(citaRq.getMotivo());

        citaRepository.save(cita);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setMensaje("Cita actualizada correctamente");
        respuesta.setStatus(200);
        respuesta.setSuccess(true);
        respuesta.setData(cita);
        return respuesta;
    }

    /**
     * Elimina una cita por su ID.
     *
     * @param id identificador de la cita.
     * @return respuesta del servicio.
     * @throws BadRequestException si la cita no existe.
     */
    @Override
    @Transactional
    public RespuestaRs eliminarCita(Integer id) throws BadRequestException {
        Optional<Cita> citaOpt = citaRepository.findById(id);
        if (citaOpt.isEmpty()) {
            throw new BadRequestException("La cita con ID " + id + " no existe");
        }

        citaRepository.deleteById(id);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setMensaje("Cita eliminada correctamente");
        respuesta.setStatus(200);
        respuesta.setSuccess(true);
        return respuesta;
    }

    @Override
    public List<Cita> listarCitasPorPaciente(Integer pacienteId) throws BadRequestException {
        Optional<Paciente> optPaciente = pacienteRepository.findById(pacienteId);
        if (optPaciente.isEmpty()) {
            throw new BadRequestException("El paciente con ID " + pacienteId + " no existe");
        }
        return citaRepository.findByPaciente(optPaciente.get());
    }
}
