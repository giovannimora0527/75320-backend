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
        
        // La lógica de este método es muy simple: delega la tarea de encontrar
        // todos los pacientes al repositorio. findAll() es un método estándar
        // de Spring Data JPA que obtiene todas las entidades de la tabla.
        return this.PacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPacientePorDocumento(String documento) throws BadRequestException {
        
        // Aquí se usa el método de consulta personalizado del repositorio.
        // findByNumeroDocumento(documento) es un método que Spring Data JPA
        // genera automáticamente para buscar por la propiedad "numeroDocumento".
        Optional<Paciente> optPaciente = this.PacienteRepository.findByNumeroDocumento(documento);
        
        // Se verifica si el objeto Optional tiene un valor. Si el paciente no se encuentra,
        // Optional.isPresent() devolverá 'false'.
        if (!optPaciente.isPresent()) {
            
            // Si el paciente no se encuentra, se lanza una excepción personalizada
            // para indicar que la solicitud no es válida.
            throw new BadRequestException("No se encuentra el paciente");
        
        }
        // Si el paciente existe, se obtiene y se devuelve del objeto Optional.
        return optPaciente.get();
    }
    
    @Override
    public List<Paciente> encontrarTodosLosPacientesXFechaNacimiento () {
        
        // Similar al primer método, este delega la lógica al repositorio.
        // Spring Data JPA interpreta el nombre del método
        // "findAllByOrderByFechaNacimientoAsc()" para encontrar todos los pacientes
        // y ordenarlos de forma ascendente por su fecha de nacimiento.
        return this.PacienteRepository.findAllByOrderByFechaNacimientoAsc();
    }

}
