package com.uniminuto.clinica.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.service.MedicoService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecializacionRepository especializacionRepository;

    @Override
    public List<Medico> buscarMedicos() {
        return this.medicoRepository.findAll();
    }

    @Override
    public List<Medico> buscarMedicosPorEspecializacion(String codEspecializacion)
            throws BadRequestException {
        Optional<Especializacion> optEsp = this.especializacionRepository
                .findByCodigoEspecializacion(codEspecializacion);

        if (!optEsp.isPresent()) {
            throw new BadRequestException("Código de especialización no válido.");
        }

        return this.medicoRepository.findByEspecializacion(optEsp.get());
    }

    @Override
    public Medico guardarMedico(Medico medico) {
        // Guarda el médico tal como viene del frontend
        return medicoRepository.save(medico);
    }

    @Override
    public Medico actualizarMedico(Long id, Medico medico) throws BadRequestException {
        Medico existente = medicoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Médico no encontrado"));

        // Actualizamos todos los campos según tu entidad
        existente.setNombres(medico.getNombres());
        existente.setApellidos(medico.getApellidos());
        existente.setTipoDocumento(medico.getTipoDocumento());
        existente.setNumeroDocumento(medico.getNumeroDocumento());
        existente.setTelefono(medico.getTelefono());
        existente.setRegistroProfesional(medico.getRegistroProfesional());
        existente.setEspecializacion(medico.getEspecializacion());

        return medicoRepository.save(existente);
    }

    @Override
    public void eliminarMedico(Long id) throws BadRequestException {
        Medico existente = medicoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Médico no encontrado"));
        medicoRepository.delete(existente);
    }
}


