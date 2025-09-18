/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.service;
import java.util.List;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;

/**
 *
 * @author Oskr
 */
public interface CitaService {
    
    Cita guardadoCita(Cita cita); 
    
    List<Cita> listadodeCitasRecientes();

    
}
