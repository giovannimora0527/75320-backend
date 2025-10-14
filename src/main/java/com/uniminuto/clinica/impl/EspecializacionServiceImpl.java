package com.uniminuto.clinica.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.service.EspecializacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecializacionServiceImpl implements EspecializacionService {

    @Autowired
    private EspecializacionRepository especializacionRepository;

    @Override
    public Especializacion crearEspecializacion(Especializacion especializacion) {
        // Generar código automáticamente si no se envía
        if (especializacion.getCodigoEspecializacion() == null || especializacion.getCodigoEspecializacion().isEmpty()) {
            especializacion.setCodigoEspecializacion("ESP-" + System.currentTimeMillis());
        }
        return especializacionRepository.save(especializacion);
    }

    @Override
    public List<Especializacion> listarEspecializaciones() {
        return especializacionRepository.findAll();
    }
}

