/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.entity.Receta;


import java.util.List;

/**
 *
 * @author Oskr
 */
public interface RecetaService {
    Receta creacionReceta(Receta receta);
    List<Receta> listadeReceta();
    Receta actualizarReceta(Long id, Receta receta);
    void eliminarReceta(Long id);
}
