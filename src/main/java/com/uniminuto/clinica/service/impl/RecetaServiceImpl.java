/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskr
 */
@Service
public class RecetaServiceImpl implements RecetaService{
    
    @Autowired
    private RecetaRepository recetaRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private CitaRepository citaRepository;
    @Override
    public Receta creacionReceta(Receta receta){
        // Buscar la cita y medicamento reales
        Cita cita = citaRepository.findById(receta.getCita().getId())
                .orElseThrow(() -> new RuntimeException("La Cita no pudo ser encontrada"));
        Medicamento medicamento = medicamentoRepository.findById(receta.getMedicamento().getId())
                .orElseThrow(() -> new RuntimeException("El Medicamento fue no encontrada"));

        receta.setCita(cita);
        receta.setMedicamento(medicamento);

        return recetaRepository.save(receta);
    }
    @Override
    public List<Receta>listadeReceta(){
        return recetaRepository.findAll();
}
    
    
}
