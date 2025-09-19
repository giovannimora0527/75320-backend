/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con recetas médicas.
 *
 * @author anago
 */
public interface RecetaService {

    /**
     * Obtiene todas las recetas ordenadas por fecha de creación descendente.
     *
     * @return Lista de recetas ordenadas de más recientes a más antiguas
     */
    List<Receta> getAllRecetasOrdenadas();

    /**
     * Obtiene recetas por ID de cita.
     *
     * @param citaId ID de la cita
     * @return Lista de recetas de la cita
     */
    List<Receta> getRecetasPorCita(Long citaId);

    /**
     * Crea una nueva receta médica.
     *
     * @param receta Objeto Receta a crear
     * @return Receta creada y guardada
     */
    Receta crearReceta(Receta receta);

    /**
     * Busca una receta por su ID.
     *
     * @param id ID de la receta
     * @return Receta encontrada
     */
    Receta getRecetaPorId(Long id);

    /**
     * Elimina una receta por su ID.
     *
     * @param id ID de la receta a eliminar
     */
    void eliminarReceta(Long id);

    /**
     * Actualiza una receta existente.
     *
     * @param id ID de la receta a actualizar
     * @param receta Receta con los datos actualizados
     * @return Receta actualizada
     */
    Receta actualizarReceta(Long id, Receta receta);
}