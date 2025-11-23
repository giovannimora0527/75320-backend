package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.service.EspecializacionService;
import lombok.RequiredArgsConstructor;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de especializaciones médicas.
 */
@Service
@RequiredArgsConstructor
public class EspecializacionServiceImpl implements EspecializacionService {

    private final EspecializacionRepository especializacionRepository;

    /**
     * Lista todas las especializaciones, ordenadas alfabéticamente por nombre.
     *
     * @return lista de especializaciones.
     */
    @Override
    public List<Especializacion> listarTodo() {
        return especializacionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Especializacion::getNombre, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    /**
     * Busca una especialización por su código único.
     *
     * @param codigo código de la especialización.
     * @return la especialización encontrada.
     * @throws BadRequestException si no se encuentra ninguna con ese código.
     */
    @Override
    public Especializacion buscarEspecializacionPorCod(String codigo) throws BadRequestException {
        Optional<Especializacion> optEsp = especializacionRepository.findByCodigoEspecializacion(codigo);
        if (optEsp.isEmpty()) {
            throw new BadRequestException("No se encuentra la especialización con código " + codigo);
        }
        return optEsp.get();
    }

    /**
     * Guarda una nueva especialización en la base de datos.
     *
     * @param especializacionRq datos de la especialización.
     * @return respuesta del servicio.
     */
    @Override
    public RespuestaRs guardarEspecializacion(EspecializacionRq especializacionRq) {
        RespuestaRs respuesta = new RespuestaRs();
        try {
            Especializacion especializacion = new Especializacion();
            especializacion.setNombre(especializacionRq.getNombre());
            especializacion.setDescripcion(especializacionRq.getDescripcion());
            especializacion.setCodigoEspecializacion(especializacionRq.getCodigoEspecialidad());

            especializacionRepository.save(especializacion);

            respuesta.setMensaje("Especialización guardada exitosamente");
            respuesta.setSuccess(true);
            respuesta.setStatus(200);
        } catch (Exception e) {
            respuesta.setMensaje("Error al guardar la especialización: " + e.getMessage());
            respuesta.setSuccess(false);
            respuesta.setStatus(500);
        }
        return respuesta;
    }

    /**
     * Actualiza una especialización existente.
     *
     * @param id                 identificador de la especialización.
     * @param especializacionRq  datos nuevos.
     * @return respuesta del servicio.
     * @throws BadRequestException si no se encuentra la especialización.
     */
    @Override
    public RespuestaRs actualizarEspecializacion(Integer id, EspecializacionRq especializacionRq)
            throws BadRequestException {
        Optional<Especializacion> especializacionOpt = especializacionRepository.findById(Long.valueOf(id));
        if (especializacionOpt.isEmpty()) {
            throw new BadRequestException("No existe la especialización con ID " + id);
        }

        Especializacion especializacion = especializacionOpt.get();
        especializacion.setNombre(especializacionRq.getNombre());
        especializacion.setDescripcion(especializacionRq.getDescripcion());
        especializacion.setCodigoEspecializacion(especializacionRq.getCodigoEspecialidad());

        especializacionRepository.save(especializacion);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setMensaje("Especialización actualizada correctamente");
        respuesta.setSuccess(true);
        respuesta.setStatus(200);
        return respuesta;
    }

    /**
     * Elimina una especialización por su ID.
     *
     * @param id identificador de la especialización.
     * @return respuesta del servicio.
     * @throws BadRequestException si no existe la especialización.
     */
    @Override
    public RespuestaRs eliminarEspecializacion(Integer id) throws BadRequestException {
        Optional<Especializacion> especializacionOpt = especializacionRepository.findById(Long.valueOf(id));
        if (especializacionOpt.isEmpty()) {
            throw new BadRequestException("La especialización con ID " + id + " no existe");
        }

        especializacionRepository.deleteById(Long.valueOf(id));

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setMensaje("Especialización eliminada correctamente");
        respuesta.setSuccess(true);
        respuesta.setStatus(200);
        return respuesta;
    }
}
