package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medico;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface MedicoService {
    List<Medico> buscarMedicos();

    List<Medico> buscarMedicosPorEspecializacion(String codEspecializacion)
            throws BadRequestException;

    Medico guardarMedico(Medico medico);

    Medico actualizarMedico(Long id, Medico medico) throws BadRequestException;

    void eliminarMedico(Long id) throws BadRequestException;
}






