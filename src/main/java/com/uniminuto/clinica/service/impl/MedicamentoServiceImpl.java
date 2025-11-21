package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.service.MedicamentoService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento buscarPorNombre(String nombre) throws BadRequestException {
        Medicamento medicamento = medicamentoRepository.findByNombre(nombre);
        if (medicamento == null) {
            throw new BadRequestException("No se encontró medicamento con nombre: " + nombre);
        }
        return medicamento;
    }

    @Override
    public RespuestaRs guardarMedicamento(MedicamentoRq medicamentoRq) throws BadRequestException {
        RespuestaRs respuesta = new RespuestaRs();

        try {
            Medicamento medicamento = new Medicamento();
            medicamento.setNombre(medicamentoRq.getNombre());
            medicamento.setDescripcion(medicamentoRq.getDescripcion());
            medicamento.setPresentacion(medicamentoRq.getPresentacion());
            medicamento.setFechaCompra(medicamentoRq.getFechaCompra());
            medicamento.setFechaVence(medicamentoRq.getFechaVence());
            medicamento.setFechaCreacionRegistro(LocalDateTime.now());
            medicamento.setFechaModificacionRegistro(LocalDateTime.now());

            medicamentoRepository.save(medicamento);

            respuesta.setMessage("Medicamento guardado exitosamente");
            respuesta.setSuccess(true);
        } catch (Exception e) {
            respuesta.setMessage("Error al guardar el medicamento: " + e.getMessage());
            respuesta.setSuccess(false);
        }

        return respuesta;
    }

    @Override
    public RespuestaRs eliminarMedicamento(Integer id) throws BadRequestException {
        RespuestaRs respuesta = new RespuestaRs();
        Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(id);

        if (medicamentoOpt.isEmpty()) {
            throw new BadRequestException("El medicamento con ID " + id + " no existe");
        }

        medicamentoRepository.deleteById(id);

        respuesta.setMessage("Medicamento eliminado correctamente");
        respuesta.setSuccess(true);
        return respuesta;
    }

    @Override
    public RespuestaRs actualizarMedicamento(Integer id, MedicamentoRq medicamentoRq) throws BadRequestException {
        RespuestaRs respuesta = new RespuestaRs();
        Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(id);

        if (medicamentoOpt.isEmpty()) {
            throw new BadRequestException("No existe el medicamento con ID " + id);
        }

        Medicamento medicamento = medicamentoOpt.get();
        medicamento.setNombre(medicamentoRq.getNombre());
        medicamento.setDescripcion(medicamentoRq.getDescripcion());
        medicamento.setPresentacion(medicamentoRq.getPresentacion());
        medicamento.setFechaCompra(medicamentoRq.getFechaCompra());
        medicamento.setFechaVence(medicamentoRq.getFechaVence());
        medicamento.setFechaModificacionRegistro(LocalDateTime.now());

        medicamentoRepository.save(medicamento);

        respuesta.setMessage("Medicamento actualizado correctamente");
        respuesta.setSuccess(true);
        return respuesta;
    }
    
    @Override
    public RespuestaRs actualizarCantidad(Integer id, Integer cantidad) throws BadRequestException {
        if (cantidad < 0) {
            throw new BadRequestException("La cantidad no puede ser negativa");
        }
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Medicamento con ID " + id + " no encontrado"));
        medicamento.setCantidad(cantidad);
        Medicamento updatedMedicamento = medicamentoRepository.save(medicamento);
        return new RespuestaRs("Cantidad del medicamento actualizada exitosamente", true, 200, updatedMedicamento);
}
}
