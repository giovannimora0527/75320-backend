/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


/**
 *
 * @author DELL
 */
@Service
public class RecetaServiceImpl implements RecetaService{
    /**
     * UsuarioRepository.
     */
    @Autowired
    private RecetaRepository RecetaRepository;

    @Override
    public List<Receta> encontrarTodasLasRecetas() {        
        return this.RecetaRepository.findAll();
    }

    @Override
    public Receta encontrarRecetaPorId(int id) 
            throws BadRequestException {
        Optional<Receta> optUser = this.RecetaRepository
                .findById(id);
        if (!optUser.isPresent()) {
            throw new BadRequestException("No se encuentra la receta.");
        }
        
        return optUser.get();
    }
    
    @Override
    public RespuestaRs guardarRecetaPorIndicaciones(RecetaRq receta) throws BadRequestException {
        this.validadorCampos(receta);
        Optional<Receta> optMedic = this.RecetaRepository
                .findByIndicaciones(receta.getIndicaciones());
        if (optMedic.isPresent()) {
            throw new BadRequestException("la receta ya existe");
        }
        Receta objGuardar = this.convertirARecetaClass(receta);
        this.RecetaRepository.save(objGuardar);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se guardo la receta satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }

    private void validadorCampos(RecetaRq receta) throws BadRequestException {
        
        if (receta.getCitaId() == null) {
            throw new BadRequestException("Cita es obligatoria");
        }
        
        if (receta.getMedicamentoId() == null ) {
            throw new BadRequestException("Medicamento es obligatoria");
        }
        if (receta.getDosis() == null || receta.getDosis().isBlank() ||
                receta.getDosis().isEmpty()) {
            throw new BadRequestException("Dosis es obligatoria");
        }
        
        if (receta.getIndicaciones() == null || receta.getIndicaciones().isBlank() ||
                receta.getIndicaciones().isEmpty()) {
            throw new BadRequestException("Indicaciones es obligatoria");
        }
    
    }

       /**
        * private Receta convertirARecetaClass(RecetaRq recetaRq){
        Receta nuevo = new Receta();
        // Cita
        Optional<Cita> optCita = this.citaRepository.findById(recetaRq.getCitaId());       
        nuevo.setCita(optCita.get());

        // Medicamento.
        Medicamento medicamento = new Medicamento();
        medicamento.setId(recetaRq.getMedicamentoId());
        nuevo.setMedicamento(medicamento);

      **/
        nuevo.setDosis(recetaRq.getDosis());
        nuevo.setIndicaciones(recetaRq.getIndicaciones());
        nuevo.setFechaCreacionRegistro(LocalDateTime.now());
        return nuevo;
    }
}