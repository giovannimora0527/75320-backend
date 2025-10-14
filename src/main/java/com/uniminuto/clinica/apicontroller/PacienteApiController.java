package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteApiController {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Listar pacientes
    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    // Crear paciente
    @PostMapping
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // Actualizar paciente
    @PutMapping("/{id}")
    public Paciente actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteRepository.findById(id)
                .map(p -> {
                    p.setTipoDocumento(paciente.getTipoDocumento());
                    p.setNumeroDocumento(paciente.getNumeroDocumento());
                    p.setNombres(paciente.getNombres());
                    p.setApellidos(paciente.getApellidos());
                    p.setTelefono(paciente.getTelefono());
                    p.setDireccion(paciente.getDireccion());
                    return pacienteRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Paciente no encontrado con id " + id));
    }

    // Eliminar paciente
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
    }
}







