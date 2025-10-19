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
 *
 * @author lmora
 */
@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository PacienteRepository;

    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return this.PacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPacientePorDocumento(String documento) throws BadRequestException {
        
        Optional<Paciente> optPaciente = this.PacienteRepository.findByNumeroDocumento(documento);
        if (!optPaciente.isPresent()) {
            throw new BadRequestException("No se encuentra el paciente");
        
        }
        return optPaciente.get();
    }

    @Override
    public RespuestaRs guardarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        this.validarCampos(pacienteRq);
        Optional<Paciente> existente = this.PacienteRepository.findByNumeroDocumento(pacienteRq.getNumeroDocumento());
        if (existente.isPresent()) {
            throw new BadRequestException("El paciente ya existe");
        }
        Paciente aGuardar = this.convertirAPaciente(pacienteRq);
        this.PacienteRepository.save(aGuardar);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se guardó el paciente satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }

    private void validarCampos(PacienteRq rq) throws BadRequestException {
        if (rq == null) {
            throw new BadRequestException("Solicitud inválida");
        }
        if (rq.getTipoDocumento() == null || rq.getTipoDocumento().trim().isEmpty()) {
            throw new BadRequestException("El tipo de documento es obligatorio");
        }
        if (rq.getNumeroDocumento() == null || rq.getNumeroDocumento().trim().isEmpty()) {
            throw new BadRequestException("El número de documento es obligatorio");
        }
        if (rq.getNombres() == null || rq.getNombres().trim().isEmpty()) {
            throw new BadRequestException("Los nombres son obligatorios");
        }
        if (rq.getApellidos() == null || rq.getApellidos().trim().isEmpty()) {
            throw new BadRequestException("Los apellidos son obligatorios");
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

    @Override
    public RespuestaRs eliminarPaciente(Integer id) throws BadRequestException {
        if (id == null) {
            throw new BadRequestException("Id inválido");
        }
        Optional<Paciente> optPac = this.PacienteRepository.findById(id);
        if (!optPac.isPresent()) {
            throw new BadRequestException("No se encuentra el paciente");
        }
        this.PacienteRepository.deleteById(id);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se eliminó el paciente satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }
    
    @Override
    public RespuestaRs actualizarPaciente(Integer id, PacienteRq pacienteRq) throws BadRequestException {
    // Validar que el ID no sea nulo
        if (id == null) {
        throw new BadRequestException("Id inválido");
        }
    
    // Validar los campos del request
    this.validarCampos(pacienteRq);
    
    // Buscar el paciente existente por ID
    Optional<Paciente> optPaciente = this.PacienteRepository.findById(id);
    if (!optPaciente.isPresent()) {
        throw new BadRequestException("No se encuentra el paciente a actualizar");
    }
    
    // Verificar que no exista otro paciente con el mismo número de documento
    Optional<Paciente> pacienteConMismoDocumento = this.PacienteRepository.findByNumeroDocumento(pacienteRq.getNumeroDocumento());
    if (pacienteConMismoDocumento.isPresent() && !pacienteConMismoDocumento.get().getId().equals(id)) {
        throw new BadRequestException("Ya existe otro paciente con ese número de documento");
    }
    
    // Obtener el paciente existente y actualizar sus datos
    Paciente pacienteExistente = optPaciente.get();
    pacienteExistente.setTipoDocumento(pacienteRq.getTipoDocumento());
    pacienteExistente.setNumeroDocumento(pacienteRq.getNumeroDocumento());
    pacienteExistente.setNombres(pacienteRq.getNombres());
    pacienteExistente.setApellidos(pacienteRq.getApellidos());
    pacienteExistente.setFechaNacimiento(pacienteRq.getFechaNacimiento());
    pacienteExistente.setGenero(pacienteRq.getGenero());
    pacienteExistente.setTelefono(pacienteRq.getTelefono());
    pacienteExistente.setDireccion(pacienteRq.getDireccion());
    
    // Guardar los cambios
    this.PacienteRepository.save(pacienteExistente);
    
    // Preparar respuesta
    RespuestaRs rta = new RespuestaRs();
    rta.setMessage("Se actualizó el paciente satisfactoriamente");
    rta.setStatus(200);
    return rta;
}

}
