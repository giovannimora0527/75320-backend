package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;

import java.util.List;

/**
 * Servicio que define las operaciones para la gestión de especializaciones médicas.
 *
 * @author lmora
 */
public interface EspecializacionService {

    /**
     * Lista todas las especializaciones registradas en el sistema.
     *
     * @return Lista de especializaciones.
     */
    List<Especializacion> listarTodo();

    /**
     * Busca una especialización por su código.
     *
     * @param codigo Código único de la especialización.
     * @return Objeto Especializacion encontrado.
     * @throws BadRequestException Si el código no existe o es inválido.
     */
    Especializacion buscarEspecializacionPorCod(String codigo) throws BadRequestException;

    /**
     * Guarda una nueva especialización.
     *
     * @param especializacionRq Datos de la especialización a registrar.
     * @return Respuesta con el resultado de la operación.
     * @throws BadRequestException Si los datos son inválidos.
     */
    RespuestaRs guardarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException;

    /**
     * Actualiza una especialización existente.
     *
     * @param id                 Identificador de la especialización.
     * @param especializacionRq  Datos actualizados.
     * @return Respuesta con el resultado de la actualización.
     * @throws BadRequestException Si el ID no existe o los datos son inválidos.
     */
    RespuestaRs actualizarEspecializacion(Integer id, EspecializacionRq especializacionRq) throws BadRequestException;

    /**
     * Elimina una especialización del sistema.
     *
     * @param id Identificador de la especialización a eliminar.
     * @return Respuesta con el resultado de la eliminación.
     * @throws BadRequestException Si la especialización no existe.
     */
    RespuestaRs eliminarEspecializacion(Integer id) throws BadRequestException;
}
