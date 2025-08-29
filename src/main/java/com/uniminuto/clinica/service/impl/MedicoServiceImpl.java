package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.service.MedicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lmora
 */
@Service
public class MedicoServiceImpl implements MedicoService {

    /**
     * Repositorio de datos para medicos.
     */    
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<Medico> encontrarTodosLosMedicos() {
        return this.medicoRepository.findAll();
    }

}
