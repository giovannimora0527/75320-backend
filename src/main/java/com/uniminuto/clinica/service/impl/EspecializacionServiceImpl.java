package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.service.EspecializacionService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecializacionServiceImpl implements EspecializacionService {

    @Autowired
    private EspecializacionRepository especializacionRepository;

    @Override
    public List<Especializacion> listarEspecializaciones() {
        return this.especializacionRepository.findAll();
    }

    @Override
    public RespuestaRs guardarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {
        this.validadorCampos(especializacionRq);

        Optional<Especializacion> optNombre = this.especializacionRepository.findByNombre(especializacionRq.getNombre());
        if (optNombre.isPresent()) {
            throw new BadRequestException("El nombre de especialización " + especializacionRq.getNombre() + " ya existe");
        }

        Optional<Especializacion> optCodigo = this.especializacionRepository.findByCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
        if (optCodigo.isPresent()) {
            throw new BadRequestException("El código de especialización " + especializacionRq.getCodigoEspecializacion() + " ya existe");
        }

        Especializacion especializacion = new Especializacion();
        especializacion.setNombre(especializacionRq.getNombre());
        especializacion.setDescripcion(especializacionRq.getDescripcion());
        especializacion.setCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
        this.especializacionRepository.save(especializacion);

        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Se ha agregado la especialización con éxito");
        return rta;
    }

    private void validadorCampos(EspecializacionRq especializacion) throws BadRequestException {
        if (especializacion.getCodigoEspecializacion() == null || especializacion.getCodigoEspecializacion().isBlank()) {
            throw new BadRequestException("El código de especialización es obligatorio");
        }
        if (especializacion.getNombre() == null || especializacion.getNombre().isBlank()) {
            throw new BadRequestException("El nombre es obligatorio");
        }
    }

    @Override
    public RespuestaRs actualizarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {
        this.validadorCampos(especializacionRq);

        Optional<Especializacion> optEspecializacion = this.especializacionRepository.findById(especializacionRq.getId());
        if (optEspecializacion.isEmpty()) {
            throw new BadRequestException("No se encontró la especialización con ID " + especializacionRq.getId());
        }

        Especializacion especializacionActualizar = optEspecializacion.get();

        if (!especializacionActualizar.getNombre().equalsIgnoreCase(especializacionRq.getNombre())) {
            Optional<Especializacion> optNombre = this.especializacionRepository.findByNombre(especializacionRq.getNombre());
            if (optNombre.isPresent()) {
                throw new BadRequestException("El nombre ya está asociado a otra especialización.");
            }
        }

        if (!especializacionActualizar.getCodigoEspecializacion().equalsIgnoreCase(especializacionRq.getCodigoEspecializacion())) {
            Optional<Especializacion> optCodigo = this.especializacionRepository.findByCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
            if (optCodigo.isPresent()) {
                throw new BadRequestException("El código de especialización ya está asociado a otra especialización.");
            }
        }

        especializacionActualizar.setNombre(especializacionRq.getNombre());
        especializacionActualizar.setDescripcion(especializacionRq.getDescripcion());
        especializacionActualizar.setCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
        this.especializacionRepository.save(especializacionActualizar);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Se ha actualizado la especialización correctamente");
        return rta;
    }
}
