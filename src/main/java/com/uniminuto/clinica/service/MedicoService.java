package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medico;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
* Servicio de medico
*/
/**
* @author Anderson
*/

public interface MedicoService {
    /**
    * Servicio para buscar medico
    */
    List<Medico> buscarMedicos();
    /**
    * Servicio para buscar medico por especializacion
    */
    List<Medico> buscarMedicosPorEspecializacion(String codEspecializacion) 
        throws BadRequestException;
}