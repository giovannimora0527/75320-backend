package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lmora
 */
@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPacientePorDocumento(String documento) throws BadRequestException {

        Optional<Paciente> optPaciente = this.pacienteRepository.findByNumeroDocumento(documento);
        if (!optPaciente.isPresent()) {
            throw new BadRequestException("No se encuentra el paciente");

        }
        return optPaciente.get();
    }

    @Override
    public List<Paciente> listarPacientesPorEdad() {
        return this.pacienteRepository.findAllByOrderByFechaNacimientoAsc();
    }

    @Override
    public RespuestaRs agregarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        this.validadorCampos(pacienteRq);
        Optional<Paciente> optUsuarioId = this.pacienteRepository.findByUsuarioId(pacienteRq.getUsuarioId());
        if (optUsuarioId.isPresent()) {
            throw new BadRequestException("El paciente con Usuario ID " + pacienteRq.getUsuarioId() + " ya existe");
        }
        Optional<Paciente> optDocumento = this.pacienteRepository.findByNumeroDocumento(pacienteRq.getNumeroDocumento());
        if (optDocumento.isPresent()) {
            throw new BadRequestException("El paciente con documento " + pacienteRq.getNumeroDocumento() + " ya existe");
        }

        Paciente paciente = new Paciente();
        paciente.setUsuarioId(pacienteRq.getUsuarioId());
        paciente.setTipoDocumento(pacienteRq.getTipoDocumento());
        paciente.setNumeroDocumento(pacienteRq.getNumeroDocumento());
        paciente.setNombres(pacienteRq.getNombres());
        paciente.setApellidos(pacienteRq.getApellidos());
        paciente.setFechaNacimiento(pacienteRq.getFechaNacimiento());
        paciente.setGenero(pacienteRq.getGenero());
        paciente.setTelefono(pacienteRq.getTelefono());
        paciente.setDireccion(pacienteRq.getDireccion());
        this.pacienteRepository.save(paciente);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Se ha agregado el paciente con éxito");
        return rta;
    }

    @Override
    public RespuestaRs actualizarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        this.validadorCampos(pacienteRq);
        Optional<Paciente> optPaciente = this.pacienteRepository.findById(pacienteRq.getId());
        if (!optPaciente.isPresent()) {
            throw new BadRequestException("No se encuentra el paciente a actualizar.");
        }

        Paciente pacienteActualizar = optPaciente.get();
        if (!pacienteActualizar.getNumeroDocumento().equals(pacienteRq.getNumeroDocumento())) {
            Optional<Paciente> optDocumento = this.pacienteRepository.findByNumeroDocumento(pacienteRq.getNumeroDocumento());
            if (optDocumento.isPresent()) {
                throw new BadRequestException("El número de documento ya está registrado para otro paciente.");
            }
        }

        if (!pacienteActualizar.getUsuarioId().equals(pacienteRq.getUsuarioId())) {
            Optional<Paciente> optUsuarioId = this.pacienteRepository.findByUsuarioId(pacienteRq.getUsuarioId());
            if (optUsuarioId.isPresent()) {
                throw new BadRequestException("El usuarioId ya está asociado a otro paciente.");
            }
        }

        pacienteActualizar.setUsuarioId(pacienteRq.getUsuarioId());
        pacienteActualizar.setTipoDocumento(pacienteRq.getTipoDocumento());
        pacienteActualizar.setNombres(pacienteRq.getNombres());
        pacienteActualizar.setApellidos(pacienteRq.getApellidos());
        pacienteActualizar.setGenero(pacienteRq.getGenero());
        pacienteActualizar.setTelefono(pacienteRq.getTelefono());
        pacienteActualizar.setDireccion(pacienteRq.getDireccion());
        this.pacienteRepository.save(pacienteActualizar);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("El paciente se ha actualizado correctamente.");
        rta.setStatus(200);
        return rta;
    }

    private void validadorCampos(PacienteRq paciente) throws BadRequestException {
        if (paciente.getUsuarioId() == null) {
            throw new BadRequestException("El usuarioId es obligatorio");
        }

        if (paciente.getTipoDocumento() == null || paciente.getTipoDocumento().isBlank() ||
                paciente.getTipoDocumento().isEmpty()) {
            throw new BadRequestException("El tipoDocumento es obligatorio");
        }

        if (paciente.getNumeroDocumento() == null || paciente.getNumeroDocumento().isBlank() ||
                paciente.getNumeroDocumento().isEmpty()) {
            throw new BadRequestException("El numeroDocumento es obligatorio");
        }

        if (paciente.getNombres() == null || paciente.getNombres().isBlank() ||
                paciente.getNombres().isEmpty()) {
            throw new BadRequestException("El nombre es obligatorio");
        }

        if (paciente.getApellidos() == null || paciente.getApellidos().isBlank() ||
                paciente.getApellidos().isEmpty()) {
            throw new BadRequestException("El apellido es obligatorio");
        }

        if (paciente.getFechaNacimiento() == null) {
            throw new BadRequestException("La fecha de nacimiento es obligatoria");
        }

        if (paciente.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new BadRequestException("La fecha de nacimiento no puede ser futura");
        }

        if (paciente.getGenero() != null && !paciente.getGenero().isBlank() && (!paciente.getGenero().equalsIgnoreCase("M") && !paciente.getGenero().equalsIgnoreCase("F"))) {
            throw new BadRequestException("El paciente no tiene el género correcto");
        }
    }
}