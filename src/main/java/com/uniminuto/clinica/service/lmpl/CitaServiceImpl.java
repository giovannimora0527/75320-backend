package com.uniminuto.clinica.service.lmpl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.service.CitaService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> encontrarTodasLasCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> encontrarCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> encontrarCitasPorPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId);
    }

    public List<Cita> encontrarCitasPorMedico(Long medicoId) {
        return citaRepository.findByMedicoId(medicoId);
    }

    public List<Cita> encontrarCitasPorEstado(String estado) {
        return citaRepository.findByEstado(estado);
    }

    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita actualizarCita(Cita cita) {
        if (!citaRepository.existsById(cita.getId())) {
            throw new IllegalArgumentException("Cita no encontrada con ID: " + cita.getId());
        }
        return citaRepository.save(cita);
    }

    public void eliminarCita(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new IllegalArgumentException("Cita no encontrada con ID: " + id);
        }
        citaRepository.deleteById(id);
    }

    public long contarCitasPorEstado(String estado) {
        return citaRepository.countByEstado(estado);
    }

    @Override
    public Cita crearCita(Cita cita) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cita> getAllCitasOrdenadas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cita> getCitasPorPaciente(Long pacienteId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cita> getCitasPorMedico(Long medicoId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean verificarDisponibilidadMedico(Long medicoId, LocalDateTime fechaHora) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cita> listarCitasRecientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
