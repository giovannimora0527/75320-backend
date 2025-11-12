package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementación del servicio para gestión de pacientes.
 * Autor: lmora
 */
@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPacientePorDocumento(String documento) throws ResponseStatusException {
        Optional<Paciente> optPaciente = this.pacienteRepository.findByNumeroDocumento(documento);
        if (optPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el paciente");
        }
        return optPaciente.get();
    }

    @Override
    public RespuestaRs guardarPaciente(PacienteRq pacienteRq) {
        validarCampos(pacienteRq);

        Optional<Paciente> existente = this.pacienteRepository.findByNumeroDocumento(pacienteRq.getNumeroDocumento());
        if (existente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El paciente ya existe");
        }

        Paciente nuevoPaciente = convertirAPaciente(pacienteRq);
        this.pacienteRepository.save(nuevoPaciente);

        RespuestaRs rta = new RespuestaRs();
        rta.setMensaje("Se guardó el paciente satisfactoriamente");
        rta.setStatus(HttpStatus.OK.value());
        return rta;
    }

    @Override
    public RespuestaRs eliminarPaciente(Integer id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id inválido");
        }

        Optional<Paciente> optPac = this.pacienteRepository.findById(id);
        if (optPac.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el paciente");
        }

        this.pacienteRepository.deleteById(id);

        RespuestaRs rta = new RespuestaRs();
        rta.setMensaje("Se eliminó el paciente satisfactoriamente");
        rta.setStatus(HttpStatus.OK.value());
        return rta;
    }

    @Override
    public RespuestaRs actualizarPaciente(Integer id, PacienteRq pacienteRq) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id inválido");
        }

        validarCampos(pacienteRq);

        Optional<Paciente> optPaciente = this.pacienteRepository.findById(id);
        if (optPaciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el paciente a actualizar");
        }

        Optional<Paciente> pacienteConMismoDocumento =
                this.pacienteRepository.findByNumeroDocumento(pacienteRq.getNumeroDocumento());
        if (pacienteConMismoDocumento.isPresent()
                && !pacienteConMismoDocumento.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe otro paciente con ese número de documento");
        }

        Paciente pacienteExistente = optPaciente.get();
        pacienteExistente.setTipoDocumento(pacienteRq.getTipoDocumento());
        pacienteExistente.setNumeroDocumento(pacienteRq.getNumeroDocumento());
        pacienteExistente.setNombres(pacienteRq.getNombres());
        pacienteExistente.setApellidos(pacienteRq.getApellidos());
        pacienteExistente.setFechaNacimiento(pacienteRq.getFechaNacimiento());
        pacienteExistente.setGenero(pacienteRq.getGenero());
        pacienteExistente.setTelefono(pacienteRq.getTelefono());
        pacienteExistente.setDireccion(pacienteRq.getDireccion());

        this.pacienteRepository.save(pacienteExistente);

        RespuestaRs rta = new RespuestaRs();
        rta.setMensaje("Se actualizó el paciente satisfactoriamente");
        rta.setStatus(HttpStatus.OK.value());
        return rta;
    }

    @Override
    public List<Paciente> listarPacientesPorEdad() {
        return this.pacienteRepository.findAllByOrderByFechaNacimientoAsc();
    }

    @Override
    public List<Paciente> listarOrdenadoPorFechaNacimiento(boolean ascendente) {
        if (ascendente) {
            return this.pacienteRepository.findAllByOrderByFechaNacimientoAsc();
        } else {
            return this.pacienteRepository.findAllByOrderByFechaNacimientoDesc();
        }
    }

    // ================= Métodos privados auxiliares =================

    private void validarCampos(PacienteRq rq) {
        if (rq == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitud inválida");
        }
        if (rq.getTipoDocumento() == null || rq.getTipoDocumento().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de documento es obligatorio");
        }
        if (rq.getNumeroDocumento() == null || rq.getNumeroDocumento().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de documento es obligatorio");
        }
        if (rq.getNombres() == null || rq.getNombres().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los nombres son obligatorios");
        }
        if (rq.getApellidos() == null || rq.getApellidos().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los apellidos son obligatorios");
        }
    }

    private Paciente convertirAPaciente(PacienteRq rq) {
        Paciente p = new Paciente();
        p.setUsuarioId(rq.getUsuarioId());
        p.setTipoDocumento(rq.getTipoDocumento());
        p.setNumeroDocumento(rq.getNumeroDocumento());
        p.setNombres(rq.getNombres());
        p.setApellidos(rq.getApellidos());
        p.setFechaNacimiento(rq.getFechaNacimiento());
        p.setGenero(rq.getGenero());
        p.setTelefono(rq.getTelefono());
        p.setDireccion(rq.getDireccion());
        return p;
    }
}
