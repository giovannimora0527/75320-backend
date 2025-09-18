/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author DELL
 */
public interface RecetaService {
    /**
     * Lista todas las recetas de la bd.
     * @return Lista de usuarios.
     */
    List<Receta> encontrarTodasLasRecetas();
    
    /**
     * Buscamos un usuario dado su username.
     * @param username username a buscar.
     * @return Usuario que cumpla con el criterio.
     */
    Receta encontrarRecetaPorId(int id) throws BadRequestException;
    
    RespuestaRs guardarRecetaPorIndicaciones(RecetaRq receta) throws BadRequestException;

}