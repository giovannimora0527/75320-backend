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
* Implementacion del servicio de paciente
*/
/**
* @author Anderson
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
    public List<Paciente> listarPacientesPorFechaNacimiento (){
        return this.pacienteRepository.findAllByOrderByFechaNacimientoAsc();
    }
    
    @Override
    public RespuestaRs guardarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        Optional<Paciente> optPaciente = this.pacienteRepository
                .findByNumeroDocumento(pacienteRq.getNumeroDocumento());
        if (optPaciente.isPresent()) {
            throw new BadRequestException("El paciente con el numero de " +
                    "documento: " + pacienteRq.getNumeroDocumento() + " ya existe.");
        }

        Paciente paciente = this.convertirRqToEntity(pacienteRq);
        this.pacienteRepository.save(paciente);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Paciente guardado con éxito.");
        return rta;
    }
    
    private Paciente convertirRqToEntity(PacienteRq pacienteRq) {
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
        return paciente;
    }
    
    @Override
    public RespuestaRs actualizarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        Optional<Paciente> pacienteExiste = this.pacienteRepository
                .findById(pacienteRq.getId());
        if (pacienteExiste.isEmpty()) {
            throw new BadRequestException("El paciente no existe y no se puede actualizar.");
        }

        Paciente pacienteActual = pacienteExiste.get();
        if (!pacienteActual.getNumeroDocumento().toLowerCase()
                .equals(pacienteRq.getNumeroDocumento())) {
            Optional<Paciente> optPaciente = this.pacienteRepository
                    .findByNumeroDocumento(pacienteRq.getNumeroDocumento());
            if (optPaciente.isPresent()) {
                throw new BadRequestException("El paciente con el numero de " +
                        "documento: " + pacienteRq.getNumeroDocumento() + " ya existe.");
            }
        }
        
        //pacienteRq
        pacienteActual.setUsuarioId(pacienteRq.getUsuarioId());
        pacienteActual.setTipoDocumento(pacienteRq.getTipoDocumento());
        pacienteActual.setNumeroDocumento(pacienteRq.getNumeroDocumento());
        pacienteActual.setNombres(pacienteRq.getNombres());
        pacienteActual.setApellidos(pacienteRq.getApellidos());
        pacienteActual.setFechaNacimiento(pacienteRq.getFechaNacimiento());
        pacienteActual.setGenero(pacienteRq.getGenero());
        pacienteActual.setTelefono(pacienteRq.getTelefono());
        pacienteActual.setDireccion(pacienteRq.getDireccion());
        this.pacienteRepository.save(pacienteActual);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Paciente actualizado con éxito.");
        return rta;
    }
}