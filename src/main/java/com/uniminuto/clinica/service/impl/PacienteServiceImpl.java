package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
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
    public List<Paciente> encontrarPacientesFNacimientoDesc() {       
        return this.PacienteRepository.findAllByOrderByFechaNacimientoAsc();
        
    }
    @Override
    public List<Paciente> buscarPacientePorGenero(String genero) {
        List<Paciente> pacientes = this.PacienteRepository.findByGenero(genero);

        return pacientes;
    }
    @Override
    public Paciente crearPaciente(Paciente p) {
        return this.PacienteRepository.save(p);
    }

    @Override
    public Paciente actualizarPaciente(Integer id, Paciente p) throws BadRequestException {
        Paciente db = this.PacienteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Paciente no encontrado con id=" + id));

        // Copia de campos editables (ajusta a tus nombres reales)
        db.setNumeroDocumento(p.getNumeroDocumento());
        db.setGenero(p.getGenero());
        db.setTelefono(p.getTelefono());
        db.setDireccion(p.getDireccion());
        db.setFechaNacimiento(p.getFechaNacimiento());
        // ...otros campos...

        return this.PacienteRepository.save(db);
    }

    @Override
    public void eliminarPaciente(Integer id) throws BadRequestException {
        if (!this.PacienteRepository.existsById(id)) {
            throw new BadRequestException("Paciente no encontrado con id=" + id);
        }
        this.PacienteRepository.deleteById(id);
    }

    @Override
    public Optional<Paciente> buscarPorIdPaciente(Integer id) {
        return this.PacienteRepository.findById(id);
    }


}
