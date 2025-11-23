package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.HistoriaMedica;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.HistoriaMedicaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.HistoriaMedicaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.HistoriaMedicaService;
import com.uniminuto.clinica.utils.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de historias médicas.
 */
@Service
@RequiredArgsConstructor
public class HistoriaMedicaServiceImpl implements HistoriaMedicaService {

    private final HistoriaMedicaRepository historiaMedicaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    @Override
    public List<HistoriaMedica> listarHistorias() {
        try {
            List<HistoriaMedica> historias = historiaMedicaRepository.findAll();
            if (historias == null) {
                return new java.util.ArrayList<>();
            }
            return historias;
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    @Override
    public HistoriaMedica obtenerPorId(Integer id) throws BadRequestException {
        return historiaMedicaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Historia médica con ID " + id + " no encontrada"));
    }

    @Override
    public List<HistoriaMedica> listarPorPaciente(Integer pacienteId) {
        try {
            return historiaMedicaRepository.findByPaciente_Id(pacienteId);
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    @Override
    public RespuestaRs guardarHistoria(HistoriaMedicaRq historiaMedicaRq) throws BadRequestException {
        if (historiaMedicaRq.getPacienteId() == null || historiaMedicaRq.getMedicoId() == null) {
            throw new BadRequestException("PacienteId y MedicoId son obligatorios");
        }

        Optional<Paciente> pacienteOpt = pacienteRepository.findById(historiaMedicaRq.getPacienteId());
        Optional<Medico> medicoOpt = medicoRepository.findById(historiaMedicaRq.getMedicoId());

        if (pacienteOpt.isEmpty()) {
            throw new BadRequestException("El paciente con ID " + historiaMedicaRq.getPacienteId() + " no existe");
        }
        if (medicoOpt.isEmpty()) {
            throw new BadRequestException("El médico con ID " + historiaMedicaRq.getMedicoId() + " no existe");
        }

        HistoriaMedica historia = new HistoriaMedica();
        historia.setPaciente(pacienteOpt.get());
        historia.setMedico(medicoOpt.get());
        historia.setFechaConsulta(historiaMedicaRq.getFechaConsulta());
        historia.setMotivoConsulta(historiaMedicaRq.getMotivoConsulta());
        historia.setSintomas(historiaMedicaRq.getSintomas());
        historia.setDiagnostico(historiaMedicaRq.getDiagnostico());
        historia.setTratamiento(historiaMedicaRq.getTratamiento());
        historia.setObservaciones(historiaMedicaRq.getObservaciones());
        historia.setFechaCreacion(LocalDateTime.now());

        historiaMedicaRepository.save(historia);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(201);
        respuesta.setMensaje("Historia médica creada correctamente");
        respuesta.setData(historia);
        return respuesta;
    }

    @Override
    public RespuestaRs actualizarHistoria(Integer id, HistoriaMedicaRq historiaMedicaRq) throws BadRequestException {
        Optional<HistoriaMedica> historiaOpt = historiaMedicaRepository.findById(id);
        if (historiaOpt.isEmpty()) {
            throw new BadRequestException("No existe la historia médica con ID " + id);
        }

        HistoriaMedica historia = historiaOpt.get();

        if (historiaMedicaRq.getPacienteId() != null) {
            Optional<Paciente> pacienteOpt = pacienteRepository.findById(historiaMedicaRq.getPacienteId());
            if (pacienteOpt.isEmpty()) {
                throw new BadRequestException("El paciente con ID " + historiaMedicaRq.getPacienteId() + " no existe");
            }
            historia.setPaciente(pacienteOpt.get());
        }

        if (historiaMedicaRq.getMedicoId() != null) {
            Optional<Medico> medicoOpt = medicoRepository.findById(historiaMedicaRq.getMedicoId());
            if (medicoOpt.isEmpty()) {
                throw new BadRequestException("El médico con ID " + historiaMedicaRq.getMedicoId() + " no existe");
            }
            historia.setMedico(medicoOpt.get());
        }

        if (historiaMedicaRq.getFechaConsulta() != null) {
            historia.setFechaConsulta(historiaMedicaRq.getFechaConsulta());
        }
        if (historiaMedicaRq.getMotivoConsulta() != null) {
            historia.setMotivoConsulta(historiaMedicaRq.getMotivoConsulta());
        }
        if (historiaMedicaRq.getSintomas() != null) {
            historia.setSintomas(historiaMedicaRq.getSintomas());
        }
        if (historiaMedicaRq.getDiagnostico() != null) {
            historia.setDiagnostico(historiaMedicaRq.getDiagnostico());
        }
        if (historiaMedicaRq.getTratamiento() != null) {
            historia.setTratamiento(historiaMedicaRq.getTratamiento());
        }
        if (historiaMedicaRq.getObservaciones() != null) {
            historia.setObservaciones(historiaMedicaRq.getObservaciones());
        }

        historia.setFechaActualizacion(LocalDateTime.now());
        historiaMedicaRepository.save(historia);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Historia médica actualizada correctamente");
        respuesta.setData(historia);
        return respuesta;
    }

    @Override
    public RespuestaRs eliminarHistoria(Integer id) throws BadRequestException {
        Optional<HistoriaMedica> historiaOpt = historiaMedicaRepository.findById(id);
        if (historiaOpt.isEmpty()) {
            throw new BadRequestException("No se encuentra la historia médica con ID " + id);
        }

        historiaMedicaRepository.delete(historiaOpt.get());

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Historia médica eliminada correctamente");
        return respuesta;
    }
}

