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

@Service
public class CitaServiceImpl implements CitaService {
    
    @Autowired
    private CitaRepository citaRepository;
  
    @Override
    public List<Cita> listarCitas() {
        return this.citaRepository.findAll();
    }

     @Override
    public RespuestaRs guardarCita(CitaRq cita) throws BadRequestException {
        this.validadorCampos(cita);

        Optional<Cita> optCitaPaciente = this.citaRepository
                .findByPacienteIdAndFechaHora(cita.getPacienteId(), cita.getFechaHora());

        if (optCitaPaciente.isPresent()) {
            throw new BadRequestException("El paciente ya tiene una cita en esa fecha y hora");
        }

       
        Optional<Cita> optCitaMedico = this.citaRepository
                .findByMedicoIdAndFechaHora(cita.getMedicoId(), cita.getFechaHora());

        if (optCitaMedico.isPresent()) {
            throw new BadRequestException("El médico ya tiene una cita en esa fecha y hora");
        }

        
        Cita objGuardar = this.convertirACitaClass(cita);
        this.citaRepository.save(objGuardar);

       
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se guardó la cita satisfactoriamente");
        rta.setStatus(200);
        return rta; 
    }
    
    
    /**
    }**/

    private void validadorCampos(CitaRq cita)throws BadRequestException {
        if (cita.getPacienteId()== null) {
            throw new BadRequestException("El ID del paciente es obligatorio");
        }
        if (cita.getMedicoId() == null) {
            throw new BadRequestException("El ID del médico es obligatorio");
        }
        if (cita.getFechaHora() == null) {
            throw new BadRequestException("La fecha y hora son obligatorias");
        }
       }
    
    
    
    
    /**
     * Convertir cita.
     */
    
    private Cita convertirACitaClass(CitaRq citaRq){
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
