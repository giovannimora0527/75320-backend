package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    @Override
    public RespuestaRs guardarCita(CitaRq citaRq) {
        var respuesta = new RespuestaRs();

        var paciente = pacienteRepository.findById(citaRq.getPacienteId());
        var medico = medicoRepository.findById(citaRq.getMedicoId());

        if (paciente.isEmpty() || medico.isEmpty()) {
            respuesta.setStatus(400);
            respuesta.setMessage("Paciente o médico no encontrado");
            return respuesta;
        }

        Cita cita = new Cita();
        cita.setPaciente(paciente.get());
        cita.setMedico(medico.get());
        cita.setFechaHora(citaRq.getFechaHora());
        cita.setEstado(citaRq.getEstado());
        cita.setMotivo(citaRq.getMotivo());

        citaRepository.save(cita);

        respuesta.setStatus(200);
        respuesta.setMessage("Cita creada correctamente");
        return respuesta;
    }
    
    @Override
    public List<Cita> listarCitaPorFechaHora() {
        return this.citaRepository.findAllOrderByFechaHoraDesc();
    }
}
