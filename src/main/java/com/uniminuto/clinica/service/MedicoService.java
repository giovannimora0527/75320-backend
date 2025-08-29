package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medico;
import java.util.List;

/**
 *
 * @author lmora
 */
public interface MedicoService {
    /**
     * Lista todos los medicos de la bd.
     * @return Lista de medicos.
     */
   List<Medico> encontrarTodosLosMedicos();  
}
