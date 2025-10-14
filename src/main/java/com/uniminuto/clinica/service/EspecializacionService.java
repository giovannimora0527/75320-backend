package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Especializacion;
import java.util.List;

public interface EspecializacionService {

    Especializacion crearEspecializacion(Especializacion especializacion);

    List<Especializacion> listarEspecializaciones();
}

