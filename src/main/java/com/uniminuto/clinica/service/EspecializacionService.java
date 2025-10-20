package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 *
 * @author lmora
 */
public interface EspecializacionService {
    List<Especializacion> listarEspecializaciones();
    RespuestaRs guardarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException;

    RespuestaRs actualizarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException;
}