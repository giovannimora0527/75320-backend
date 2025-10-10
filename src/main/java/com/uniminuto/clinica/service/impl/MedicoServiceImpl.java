package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.service.MedicoService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lmora
 */
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
    public List<Medico> buscarMedicosPorEspecializacion(
            String codEspecializacion) 
            throws BadRequestException {
        Optional<Especializacion> optEsp = this.especializacionRepository
                .findByCodigoEspecializacion(codEspecializacion);
        
        if (!optEsp.isPresent()) {
            throw new BadRequestException("Codigo de especializacion no valido.");
        }
        
        return this.medicoRepository.findByEspecializacion(optEsp.get());
    }

    @Override
    public RespuestaRs guardarMedico(MedicoRq medicoRq) throws BadRequestException {
        Optional<Medico> optMedico = this.medicoRepository
                .findByNumeroDocumento(medicoRq.getNumeroDocumento());
        if (optMedico.isPresent()) {
            throw new BadRequestException("El médico con el numero de " +
                    "documento: " + medicoRq.getNumeroDocumento() + " ya existe.");
        }

        optMedico = this.medicoRepository.findByRegistroProfesional(medicoRq.getRegistroProfesional());
        if (optMedico.isPresent()) {
            throw new BadRequestException("El médico con el numero de " +
                    "registro profesional: " + medicoRq.getRegistroProfesional() + " ya existe.");
        }

        Optional<Especializacion> optEspecializacion = this.especializacionRepository
                .findById(medicoRq.getEspecializacionId());
        if (optEspecializacion.isEmpty()) {
            throw new BadRequestException("La especializacion no es valida.");
        }

        Medico medico = this.convertirRqToEntity(medicoRq, optEspecializacion.get());
        this.medicoRepository.save(medico);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Médico guardado con éxito.");
        return rta;
    }

    private Medico convertirRqToEntity(MedicoRq medicoRq, Especializacion especializacion) {
        Medico medico = new Medico();
        medico.setTipoDocumento(medicoRq.getTipoDocumento());
        medico.setNumeroDocumento(medicoRq.getNumeroDocumento());
        medico.setNombres(medicoRq.getNombres());
        medico.setApellidos(medicoRq.getApellidos());
        medico.setTelefono(medicoRq.getTelefono());
        medico.setRegistroProfesional(medicoRq.getRegistroProfesional());
        medico.setEspecializacion(especializacion);
        return medico;
    }

    @Override
    public RespuestaRs actualizarMedico(MedicoRq medicoRq) throws BadRequestException {
        Optional<Medico> medicoExiste = this.medicoRepository
                .findById(medicoRq.getId());
        if (medicoExiste.isEmpty()) {
            throw new BadRequestException("El médico no existe y no se puede actualizar.");
        }

        Medico medicoActual = medicoExiste.get();
        if (!medicoActual.getNumeroDocumento().toLowerCase()
                .equals(medicoRq.getNumeroDocumento())) {
            Optional<Medico> optMedico = this.medicoRepository
                    .findByNumeroDocumento(medicoRq.getNumeroDocumento());
            if (optMedico.isPresent()) {
                throw new BadRequestException("El médico con el numero de " +
                        "documento: " + medicoRq.getNumeroDocumento() + " ya existe.");
            }
        }

        if (!medicoActual.getRegistroProfesional().toLowerCase()
                .equals(medicoRq.getRegistroProfesional().toLowerCase())) {
            Optional<Medico> optMedico = this.medicoRepository
                    .findByRegistroProfesional(medicoRq.getRegistroProfesional());
            if (optMedico.isPresent()) {
                throw new BadRequestException("El médico con el numero de " +
                        "registro profesional: " + medicoRq.getRegistroProfesional() + " ya existe.");
            }
        }

        Optional<Especializacion> optEspecializacion = this.especializacionRepository
                .findById(medicoRq.getEspecializacionId());
        if (optEspecializacion.isEmpty()) {
            throw new BadRequestException("La especializacion no es valida.");
        }

        //medicoRq
        medicoActual.setTipoDocumento(medicoRq.getTipoDocumento());
        medicoActual.setNumeroDocumento(medicoRq.getNumeroDocumento());
        medicoActual.setNombres(medicoRq.getNombres());
        medicoActual.setApellidos(medicoRq.getApellidos());
        medicoActual.setTelefono(medicoRq.getTelefono());
        medicoActual.setRegistroProfesional(medicoRq.getRegistroProfesional());
        medicoActual.setEspecializacion(optEspecializacion.get());
        this.medicoRepository.save(medicoActual);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Médico actualizado con éxito.");
        return rta;
    }

}
