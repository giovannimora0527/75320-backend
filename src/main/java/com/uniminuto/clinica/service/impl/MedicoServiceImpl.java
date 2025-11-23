package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.repository.MedicoRepository;
import com.uniminuto.clinica.service.EspecializacionService;
import com.uniminuto.clinica.service.MedicoService;
import lombok.RequiredArgsConstructor;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de médicos.
 * Maneja las operaciones CRUD y consultas específicas.
 */
@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final EspecializacionService especializacionService;
    private final EspecializacionRepository especializacionRepository;

    /**
     * Lista todos los médicos registrados.
     *
     * @return lista de médicos.
     */
    @Override
    public List<Medico> listarMedicos() {
        try {
            return this.medicoRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error al listar médicos: " + e.getMessage());
            throw new RuntimeException("Error al obtener la lista de médicos", e);
        }
    }

    /**
     * Busca médicos por código de especialización.
     *
     * @param codigo código de especialización.
     * @return lista de médicos asociados.
     * @throws BadRequestException si la especialización no existe.
     */
    @Override
    public List<Medico> buscarPorEspecialidad(String codigo) throws BadRequestException {
        Especializacion especializacion = this.especializacionService.buscarEspecializacionPorCod(codigo);
        return this.medicoRepository.findByEspecializacion(especializacion);
    }

    /**
     * Guarda un nuevo médico en el sistema.
     *
     * @param medicoRq datos del médico a guardar.
     * @return respuesta del servicio.
     * @throws BadRequestException si ya existe un médico con el mismo documento o registro profesional.
     */
    @Override
    public RespuestaRs guardarMedico(MedicoRq medicoRq) throws BadRequestException {
        Optional<Medico> optMedico = this.medicoRepository.findByDocumento(medicoRq.getNumeroDocumento());
        if (optMedico.isPresent()) {
            throw new BadRequestException("El número de documento ya está registrado");
        }

        optMedico = this.medicoRepository.findByRegistroProfesional(medicoRq.getRegistroProfesional());
        if (optMedico.isPresent()) {
            throw new BadRequestException("Existe otro médico con el registro profesional ingresado");
        }

        Optional<Especializacion> optEsp = this.especializacionRepository.findById(Long.valueOf(medicoRq.getEspecializacionId()));
        if (optEsp.isEmpty()) {
            throw new BadRequestException("La especialización especificada no existe");
        }

        Medico medicoGuardar = this.convertToEntity(medicoRq, optEsp.get());
        this.medicoRepository.save(medicoGuardar);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Médico guardado exitosamente");
        respuesta.setSuccess(true);
        return respuesta;
    }

    /**
     * Actualiza los datos de un médico existente.
     *
     * @param id identificador del médico.
     * @param medicoRq datos actualizados.
     * @return respuesta del servicio.
     * @throws BadRequestException si el médico no existe.
     */
    @Override
    public RespuestaRs actualizarMedico(Integer id, MedicoRq medicoRq) throws BadRequestException {
        Optional<Medico> optMedico = this.medicoRepository.findById(id);
        if (optMedico.isEmpty()) {
            throw new BadRequestException("No existe el médico con ID " + id);
        }

        Medico medico = optMedico.get();
        Optional<Especializacion> optEsp = this.especializacionRepository.findById(Long.valueOf(medicoRq.getEspecializacionId()));
        if (optEsp.isEmpty()) {
            throw new BadRequestException("La especialización especificada no existe");
        }

        medico.setNombres(medicoRq.getNombres());
        medico.setApellidos(medicoRq.getApellidos());
        medico.setTelefono(medicoRq.getTelefono());
        medico.setDocumento(medicoRq.getNumeroDocumento());
        medico.setTipoDocumento(medicoRq.getTipoDocumento());
        medico.setRegistroProfesional(medicoRq.getRegistroProfesional());
        medico.setEspecializacion(optEsp.get());

        this.medicoRepository.save(medico);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Médico actualizado correctamente");
        respuesta.setSuccess(true);
        return respuesta;
    }

    /**
     * Convierte un objeto {@link MedicoRq} en una entidad {@link Medico}.
     *
     * @param medicoRq datos del request.
     * @param especializacion especialización asociada.
     * @return entidad lista para persistir.
     */
    private Medico convertToEntity(MedicoRq medicoRq, Especializacion especializacion) {
        Medico medico = new Medico();
        medico.setNombres(medicoRq.getNombres());
        medico.setApellidos(medicoRq.getApellidos());
        medico.setDocumento(medicoRq.getNumeroDocumento());
        medico.setTipoDocumento(medicoRq.getTipoDocumento());
        medico.setRegistroProfesional(medicoRq.getRegistroProfesional());
        medico.setEspecializacion(especializacion);
        medico.setTelefono(medicoRq.getTelefono());
        return medico;
    }

    @Override
    public RespuestaRs eliminarMedico(Integer id) throws BadRequestException {
        Optional<Medico> optMedico = this.medicoRepository.findById(id);
        if (optMedico.isEmpty()) {
            throw new BadRequestException("No existe el médico con ID " + id);
        }

        this.medicoRepository.deleteById(id);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Médico eliminado correctamente");
        respuesta.setSuccess(true);
        return respuesta;
    }
}
