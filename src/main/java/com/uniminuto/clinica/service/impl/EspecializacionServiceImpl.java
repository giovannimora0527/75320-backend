package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.service.EspecializacionService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecializacionServiceImpl implements EspecializacionService {

    @Autowired
    private EspecializacionRepository especializacionRepository;

    @Override
    public List<Especializacion> buscarEspecializacion() {
        return especializacionRepository.findAll();
    }
    
    @Override
    public RespuestaRs guardarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {
        RespuestaRs respuesta = new RespuestaRs();

        try {
            Especializacion especializacion = new Especializacion();
            especializacion.setNombre(especializacionRq.getNombre());
            especializacion.setDescripcion(especializacionRq.getDescripcion());
            especializacion.setCodigoEspecializacion(especializacionRq.getCodigoEspecialidad());

            especializacionRepository.save(especializacion);

            respuesta.setMessage("Especializacion guardada exitosamente");
            respuesta.setSuccess(true);
        } catch (Exception e) {
            respuesta.setMessage("Error al guardar el Especialidad: " + e.getMessage());
            respuesta.setSuccess(false);
        }

        return respuesta;
    }

    @Override
    public RespuestaRs eliminarEspecializacion(Integer id) throws BadRequestException {
        RespuestaRs respuesta = new RespuestaRs();
        Optional<Especializacion> especializacionOpt = especializacionRepository.findById(id);

        if (especializacionOpt.isEmpty()) {
            throw new BadRequestException("La Especializacion con ID " + id + " no existe");
        }

        especializacionRepository.deleteById(id);

        respuesta.setMessage("Especializacion eliminada correctamente");
        respuesta.setSuccess(true);
        return respuesta;
    }

    @Override
    public RespuestaRs actualizarEspecializacion(Integer id, EspecializacionRq especializacionRq) throws BadRequestException {
        RespuestaRs respuesta = new RespuestaRs();
        Optional<Especializacion> especializacionOpt = especializacionRepository.findById(id);

        if (especializacionOpt.isEmpty()) {
            throw new BadRequestException("No existe la Especializacion con ID " + id);
        }

        Especializacion especializacion = especializacionOpt.get();
        especializacion.setNombre(especializacionRq.getNombre());
        especializacion.setDescripcion(especializacionRq.getDescripcion());
        especializacion.setCodigoEspecializacion(especializacionRq.getCodigoEspecialidad());

        especializacionRepository.save(especializacion);

        respuesta.setMessage("Especializacion actualizada correctamente");
        respuesta.setSuccess(true);
        return respuesta;
    }
}
