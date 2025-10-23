package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Especializacion;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 * Servicio para la gestión de especializaciones médicas
 * 
 * @author JulianLopez
 */
public interface EspecializacionService {
    
    /**
     * Lista todas las especializaciones médicas
     * 
     * @return Lista de especializaciones
     */
    List<Especializacion> encontrarTodasLasEspecializaciones();
    
    /**
     * Busca una especialización por su ID
     * 
     * @param id ID de la especialización
     * @return Especialización encontrada
     * @throws BadRequestException si no se encuentra la especialización
     */
    Especializacion buscarEspecializacionPorId(Integer id) throws BadRequestException;
    
    /**
     * Crea una nueva especialización médica
     * 
     * @param especializacion Especialización a crear
     * @return Especialización creada
     * @throws BadRequestException si hay errores en los datos
     */
    Especializacion crearEspecializacion(Especializacion especializacion) throws BadRequestException;
    
    /**
     * Actualiza una especialización existente
     * 
     * @param id ID de la especialización a actualizar
     * @param especializacion Datos actualizados
     * @return Especialización actualizada
     * @throws BadRequestException si no se encuentra la especialización
     */
    Especializacion actualizarEspecializacion(Integer id, Especializacion especializacion) throws BadRequestException;
    
    /**
     * Elimina una especialización por su ID
     * 
     * @param id ID de la especialización a eliminar
     * @throws BadRequestException si no se encuentra la especialización
     */
    void eliminarEspecializacion(Integer id) throws BadRequestException;
}