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

/**
 *
 * @author lmora
 */
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
        Optional<Especializacion> optEspecializacion = this.especializacionRepository
                .findByCodigoEspecializacion(especializacionRq.getCodigo());
        if (optEspecializacion.isPresent()) {
            throw new BadRequestException("Ya existe una especializacion con el codigo "
                    + especializacionRq.getCodigo());
        }
        optEspecializacion = this.especializacionRepository
                .findByNombre(especializacionRq.getNombre());
        if (optEspecializacion.isPresent()) {
            throw new BadRequestException("Ya existe una especializacion con el nombre "
                    + especializacionRq.getNombre());
        }

        Especializacion esp = this.convertirEspecializacionRqAEntidad(especializacionRq);
        this.especializacionRepository.save(esp);
        RespuestaRs respuestaRs = new RespuestaRs();
        respuestaRs.setStatus(200);
        respuestaRs.setMessage("Especializacion guardada exitosamente");
        return respuestaRs;
    }

    private Especializacion convertirEspecializacionRqAEntidad(EspecializacionRq especializacionRq) {
        Especializacion especializacion = new Especializacion();
        especializacion.setCodigoEspecializacion(especializacionRq.getCodigo());
        especializacion.setNombre(especializacionRq.getNombre());
        especializacion.setDescripcion(especializacionRq.getDescripcion());
        return especializacion;
    }

    @Override
    public RespuestaRs actualizarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {
        Optional<Especializacion> optEspecializacion = this.especializacionRepository
                .findById(especializacionRq.getId());
        if (optEspecializacion.isEmpty()) {
            throw new BadRequestException("No existe una especializacion con el id "
                    + especializacionRq.getId());
        }

        Especializacion especializacionActual = optEspecializacion.get();
        if (!especializacionActual.getNombre().equalsIgnoreCase(especializacionRq.getNombre())) {
            optEspecializacion = this.especializacionRepository
                    .findByNombre(especializacionRq.getNombre());
            if (optEspecializacion.isPresent()) {
                throw new BadRequestException("Ya existe una especializacion con el nombre "
                        + especializacionRq.getNombre());
            }
            especializacionActual.setNombre(especializacionRq.getNombre());
        }

        if (!especializacionActual.getCodigoEspecializacion()
                .equalsIgnoreCase(especializacionRq.getCodigo())) {
            optEspecializacion = this.especializacionRepository
                    .findByCodigoEspecializacion(especializacionRq.getCodigo());
            if (optEspecializacion.isPresent()) {
                throw new BadRequestException("Ya existe una especializacion con el código "
                        + especializacionRq.getNombre());
            }
            especializacionActual.setCodigoEspecializacion(especializacionRq.getCodigo());
        }
        this.especializacionRepository.save(especializacionActual);
        RespuestaRs respuestaRs = new RespuestaRs();
        respuestaRs.setStatus(200);
        respuestaRs.setMessage("Especializacion actualizada exitosamente");
        return respuestaRs;
    }
}
