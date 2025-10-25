package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestión de pacientes.
 * Corrige el manejo de usuarioId y evita errores de constraint en base de datos.
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
        if (optPaciente.isEmpty()) {
            throw new BadRequestException("No se encuentra el paciente");
        }
        return optPaciente.get();
    }

    @Override
    public List<Paciente> listarPacientesPorEdad() {
        return this.pacienteRepository.findAllByOrderByFechaNacimientoAsc();
    }

    @Override
    public RespuestaRs guardarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        // Validar duplicado por documento
        Optional<Paciente> optPaciente = this.pacienteRepository
                .findByNumeroDocumento(pacienteRq.getNumeroDocumento());
        if (optPaciente.isPresent()) {
            throw new BadRequestException("El paciente con el número de documento: "
                    + pacienteRq.getNumeroDocumento() + " ya existe.");
        }

        // Convertir RQ → Entity
        Paciente paciente = this.convertirRqToEntity(pacienteRq);

        if (pacienteRq.getUsuarioId() != null && pacienteRq.getUsuarioId() > 0) {
            paciente.setUsuarioId(Math.toIntExact(pacienteRq.getUsuarioId()));
        } else {
            paciente.setUsuarioId(null);
        }

        this.pacienteRepository.save(paciente);

        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Paciente guardado con éxito.");
        return rta;
    }

    @Override
    public RespuestaRs actualizarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        Optional<Paciente> pacienteExiste = this.pacienteRepository.findById(pacienteRq.getId());
        if (pacienteExiste.isEmpty()) {
            throw new BadRequestException("El paciente no existe y no se puede actualizar.");
        }

        Paciente pacienteActual = pacienteExiste.get();

        // Verificar duplicado de documento si cambia
        if (!pacienteActual.getNumeroDocumento().equalsIgnoreCase(pacienteRq.getNumeroDocumento())) {
            Optional<Paciente> optPaciente = this.pacienteRepository
                    .findByNumeroDocumento(pacienteRq.getNumeroDocumento());
            if (optPaciente.isPresent()) {
                throw new BadRequestException("El paciente con el número de documento: "
                        + pacienteRq.getNumeroDocumento() + " ya existe.");
            }
        }


        pacienteActual.setTipoDocumento(pacienteRq.getTipoDocumento());
        pacienteActual.setNumeroDocumento(pacienteRq.getNumeroDocumento());
        pacienteActual.setNombres(pacienteRq.getNombres());
        pacienteActual.setApellidos(pacienteRq.getApellidos());
        pacienteActual.setFechaNacimiento(pacienteRq.getFechaNacimiento());
        pacienteActual.setGenero(pacienteRq.getGenero());
        pacienteActual.setTelefono(pacienteRq.getTelefono());
        pacienteActual.setDireccion(pacienteRq.getDireccion());


        if (pacienteRq.getUsuarioId() != null && pacienteRq.getUsuarioId() > 0) {
            pacienteActual.setUsuarioId(Math.toIntExact(pacienteRq.getUsuarioId()));
        } else {
            pacienteActual.setUsuarioId(null);
        }

        this.pacienteRepository.save(pacienteActual);

        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Paciente actualizado con éxito.");
        return rta;
    }

    /**
     * Convierte un objeto de solicitud (PacienteRq) en entidad (Paciente).
     */
    private Paciente convertirRqToEntity(PacienteRq pacienteRq) {
        Paciente p = new Paciente();
        p.setTipoDocumento(pacienteRq.getTipoDocumento());
        p.setNumeroDocumento(pacienteRq.getNumeroDocumento());
        p.setNombres(pacienteRq.getNombres());
        p.setApellidos(pacienteRq.getApellidos());
        p.setFechaNacimiento(pacienteRq.getFechaNacimiento());
        p.setGenero(pacienteRq.getGenero());
        p.setTelefono(pacienteRq.getTelefono());
        p.setDireccion(pacienteRq.getDireccion());
        // UsuarioId se maneja en el método principal
        return p;
    }
}