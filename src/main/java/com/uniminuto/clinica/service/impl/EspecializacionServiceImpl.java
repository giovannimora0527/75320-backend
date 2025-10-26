package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.service.EspecializacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;


@Service
public class EspecializacionServiceImpl implements EspecializacionService {

    @Autowired
    private EspecializacionRepository especializacionRepository;

    @Override
    public List<Especializacion> listarEspecializaciones() {
        return this.especializacionRepository.findAll();
    }
    /*
    Impl guardar especializacion
    */

    @Override
    public RespuestaRs guardarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {

        Optional<Especializacion> optNombre = this.especializacionRepository
                .findByNombre(especializacionRq.getNombre());
        if (optNombre.isPresent()) {
            throw new BadRequestException("Ya existe una especializacion con ese nombre");
        }


        Optional<Especializacion> optCodigo = this.especializacionRepository
                .findByCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
        if (optCodigo.isPresent()) {
            throw new BadRequestException("Ya existe una especialización con ese código.");
        }

        Especializacion especializacion = this.convertirRqToEntity(especializacionRq);
        this.especializacionRepository.save(especializacion);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Especializacion guardada con éxito.");
        return rta;
    }

    private Especializacion convertirRqToEntity(EspecializacionRq especializacionRq) {
        Especializacion especializacion = new Especializacion();

        especializacion.setNombre(especializacionRq.getNombre());
        especializacion.setDescripcion(especializacionRq.getDescripcion());
        especializacion.setCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
        return especializacion;
    }


    /*
    Impl actualizar especializacion
    */

    @Override
    public RespuestaRs actualizarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {

        Optional<Especializacion> especializacionExiste = this.especializacionRepository
                .findById(especializacionRq.getId());

        if (especializacionExiste.isEmpty()) {
            throw new BadRequestException("La especialización no existe y no se puede actualizar.");
        }
        Especializacion especializacionActual = especializacionExiste.get();

        // Validar si cambia el código
        if (!especializacionActual.getCodigoEspecializacion()
                .equalsIgnoreCase(especializacionRq.getCodigoEspecializacion())) {
            Optional<Especializacion> optCodigo = this.especializacionRepository
                    .findByCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
            if (optCodigo.isPresent()) {
                throw new BadRequestException("El código de especialización: "
                        + especializacionRq.getCodigoEspecializacion() + " ya existe.");
            }
        }

        // Validar si cambia el nombre
        if (!especializacionActual.getNombre()
                .equalsIgnoreCase(especializacionRq.getNombre())) {
            Optional<Especializacion> optNombre = this.especializacionRepository
                    .findByNombre(especializacionRq.getNombre());
            if (optNombre.isPresent()) {
                throw new BadRequestException("El nombre de especialización: "
                        + especializacionRq.getNombre() + " ya existe.");
            }
        }
        // Actualizar campos
        especializacionActual.setNombre(especializacionRq.getNombre());
        especializacionActual.setDescripcion(especializacionRq.getDescripcion());
        especializacionActual.setCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());

        this.especializacionRepository.save(especializacionActual);

        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Especialización actualizada con éxito.");
        return rta;
    }


}
