/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.service.CitaService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andre
 */


@Service
public class CitaServiceImpl implements CitaService{
    
    
    @Autowired
    private CitaRepository citaRepository;

    /**
     * 
     * Metodo listar.
     */
    
    @Override
    public List<Cita> listarCita() {
       return citaRepository.findAll();
    }

    /**
    * Metodo Guardar.
    */
    @Override
    public RespuestaRs guardarCita(CitaRq cita) throws BadRequestException {
        this.validadorCampos(cita);
        
        /**
         * valida si existe una cita para el medico en esa fecha.
         */
        Optional<Cita> optCita = this.citaRepository
            .findByMedico_IdAndFechaHora(cita.getMedicoId(),cita.getFechaHora());
        if (optCita.isPresent()) {
            throw new BadRequestException("El medico ya tiene una cita en ese horario");
        }
        /**
         * Guarda el objeto.
         */
        
        Cita objGuardar = this.convertirCitaClass(cita);
        this.citaRepository.save(objGuardar);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Cita guardada exitosamente");
        rta.setStatus(200);
        return rta;
        
        
        
    }
    /**
     * Lista por fecha
    */
    
    @Override
    public List<Cita>ListarCitasOrdenadas(){
        return citaRepository.findAllByOrderByFechaHoraDesc();
        
    }
    
    
    /**
     * Validador de campos.
     */
    
    private void validadorCampos(CitaRq cita)throws BadRequestException{
        if (cita.getPacienteId() == null) {
            throw new BadRequestException("El ID del paciente es obligatorio");
        }
        if (cita.getMedicoId() == null)  {
            throw new BadRequestException("El Id del medico es obligatorio");
        }

        if (cita.getFechaHora() == null) {
        throw new BadRequestException("La fecha y hora son obligatorias");
        }

        if (cita.getEstado() == null || cita.getEstado().isBlank()) {
        throw new BadRequestException("El estado es obligatorio");
        }

        if (cita.getMotivo() == null || cita.getMotivo().isBlank()) {
        throw new BadRequestException("El motivo es obligatorio");
        }   
    }
    
    
    
    /**
     * Convertir cita.
     */
    
    private Cita convertirCitaClass(CitaRq citaRq){
        Cita nuevo = new Cita();
        // Paciente
        Paciente paciente = new Paciente();
        paciente.setId(citaRq.getPacienteId());
        nuevo.setPaciente(paciente);

        // Medico.
        Medico medico = new Medico();
        medico.setId(citaRq.getMedicoId());
        nuevo.setMedico(medico);
         
        // otros campos seteados.
        nuevo.setFechaHora(citaRq.getFechaHora());
        nuevo.setEstado(citaRq.getEstado());
        nuevo.setMotivo(citaRq.getMotivo());
        return nuevo;
        
 
    }
}
