package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface EspecializacionService {
    
    List<Especializacion> buscarEspecializacion();
    RespuestaRs guardarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException;
    RespuestaRs eliminarEspecializacion(Integer id) throws BadRequestException;
    RespuestaRs actualizarEspecializacion(Integer id, EspecializacionRq especializacionRq) throws BadRequestException;
    
}
