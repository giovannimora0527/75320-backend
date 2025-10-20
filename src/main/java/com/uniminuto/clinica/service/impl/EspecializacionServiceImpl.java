package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
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
                .findByCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
        if (optEspecializacion.isPresent()) {
            throw new BadRequestException("La Especializacion con el codigo de especializacion ya existe: "
                    + especializacionRq.getCodigoEspecializacion() + " ya existe.");
        }
        optEspecializacion= this.especializacionRepository.findByNombre(especializacionRq.getNombre());
        if (optEspecializacion.isPresent()) {
            throw new BadRequestException("la especializacion con el " +
                    "nombre: " + especializacionRq.getNombre() + " ya existe.");

        }
        Especializacion especializacion = this.convertirRqToEntity(especializacionRq);
        this.especializacionRepository.save(especializacion);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Especializacion guardado con éxito.");
        return rta;
    }

    private Especializacion convertirRqToEntity(EspecializacionRq especializacionRq) {
        Especializacion especializacion = new Especializacion();
        especializacion.setCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());
        especializacion.setNombre(especializacionRq.getNombre());
        especializacion.setDescripcion(especializacionRq.getDescripcion());
        return especializacion;
    }

    @Override
    public RespuestaRs actualizarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {
        // Buscar la especialización por ID (identificador único)
        Optional<Especializacion> especializacionExiste = this.especializacionRepository
                .findById(especializacionRq.getId());

        if (especializacionExiste.isEmpty()) {
            throw new BadRequestException("La especializacion no existe y no se puede actualizar.");
        }

        Especializacion especializacionActual = especializacionExiste.get();

        // Validar si el nombre fue cambiado y ya existe en otra especialización
        if (!especializacionActual.getNombre().equalsIgnoreCase(especializacionRq.getNombre())) {
            Optional<Especializacion> optEspecializacionNombre = this.especializacionRepository
                    .findByNombre(especializacionRq.getNombre());

            if (optEspecializacionNombre.isPresent()
                    && !optEspecializacionNombre.get().getId().equals(especializacionActual.getId())) {
                throw new BadRequestException("La especializacion con el nombre: "
                        + especializacionRq.getNombre() + " ya existe.");
            }
        }

        // Validar si el código fue cambiado y ya existe en otra especialización
        if (!especializacionActual.getCodigoEspecializacion().equalsIgnoreCase(especializacionRq.getCodigoEspecializacion())) {
            Optional<Especializacion> optEspecializacionCodigo = this.especializacionRepository
                    .findByCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());

            if (optEspecializacionCodigo.isPresent()
                    && !optEspecializacionCodigo.get().getId().equals(especializacionActual.getId())) {
                throw new BadRequestException("La especializacion con el código: "
                        + especializacionRq.getCodigoEspecializacion() + " ya existe.");
            }
        }

        // Actualizar los campos
        especializacionActual.setDescripcion(especializacionRq.getDescripcion());
        especializacionActual.setNombre(especializacionRq.getNombre());
        especializacionActual.setCodigoEspecializacion(especializacionRq.getCodigoEspecializacion());

        this.especializacionRepository.save(especializacionActual);

        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Especializacion actualizada con éxito.");
        return rta;
    }
}