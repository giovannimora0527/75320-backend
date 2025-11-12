package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.service.MedicamentoService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll()
                .stream()
                .sorted((m1, m2) -> m2.getFechaCompra().compareTo(m1.getFechaCompra()))
                .toList();
    }

    @Override
    public Medicamento buscarPorNombre(String nombre) throws BadRequestException {
        Optional<Medicamento> medicamentoOpt = medicamentoRepository.findByNombre(nombre);
        if (medicamentoOpt.isEmpty()) {
            throw new BadRequestException("No se encontró medicamento con nombre: " + nombre);
        }
        return medicamentoOpt.get();
    }

    @Override
    public RespuestaRs guardarMedicamento(MedicamentoRq medicamentoRq) throws BadRequestException {
        validarFormulario(medicamentoRq);

        Optional<Medicamento> optMedicamento = medicamentoRepository.findByNombre(medicamentoRq.getNombre());
        if (optMedicamento.isPresent()) {
            throw new BadRequestException("El medicamento ya existe");
        }

        Medicamento nuevo = mapearAMedicamento(medicamentoRq);
        medicamentoRepository.save(nuevo);

        return new RespuestaRs("Medicamento guardado exitosamente", true, 200, nuevo);
    }

    @Override
    public RespuestaRs actualizarMedicamento(Integer id, MedicamentoRq medicamentoRq) throws BadRequestException {
        if (id == null) {
            throw new BadRequestException("El id del medicamento es obligatorio");
        }

        Optional<Medicamento> optMedicamento = medicamentoRepository.findById(id);
        if (optMedicamento.isEmpty()) {
            throw new BadRequestException("El medicamento con ID " + id + " no existe");
        }

        Medicamento medicamento = optMedicamento.get();

        if (!medicamento.getNombre().equalsIgnoreCase(medicamentoRq.getNombre())) {
            Optional<Medicamento> existeNombre = medicamentoRepository.findByNombre(medicamentoRq.getNombre());
            if (existeNombre.isPresent()) {
                throw new BadRequestException("El nombre del medicamento ya existe");
            }
        }

        medicamento.setNombre(medicamentoRq.getNombre() != null ? medicamentoRq.getNombre() : medicamento.getNombre());
        medicamento.setDescripcion(medicamentoRq.getDescripcion() != null ? medicamentoRq.getDescripcion() : medicamento.getDescripcion());
        medicamento.setPresentacion(medicamentoRq.getPresentacion() != null ? medicamentoRq.getPresentacion() : medicamento.getPresentacion());
        medicamento.setFechaCompra(medicamentoRq.getFechaCompra() != null ? medicamentoRq.getFechaCompra() : medicamento.getFechaCompra());
        medicamento.setFechaVence(medicamentoRq.getFechaVence() != null ? medicamentoRq.getFechaVence() : medicamento.getFechaVence());
        medicamento.setFechaModificacionRegistro(LocalDateTime.now());

        medicamentoRepository.save(medicamento);

        return new RespuestaRs("Medicamento actualizado correctamente", true, 200, medicamento);
    }

    @Override
    public RespuestaRs eliminarMedicamento(Integer id) throws BadRequestException {
        Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(id);

        if (medicamentoOpt.isEmpty()) {
            throw new BadRequestException("El medicamento con ID " + id + " no existe");
        }

        medicamentoRepository.deleteById(id);
        return new RespuestaRs("Medicamento eliminado correctamente", true, 200, null);
    }

    @Override
    public RespuestaRs actualizarCantidad(Integer id, Integer cantidad) throws BadRequestException {
        if (cantidad == null || cantidad < 0) {
            throw new BadRequestException("La cantidad no puede ser negativa ni nula");
        }

        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Medicamento con ID " + id + " no encontrado"));

        medicamento.setCantidad(cantidad);
        medicamento.setFechaModificacionRegistro(LocalDateTime.now());
        medicamentoRepository.save(medicamento);

        return new RespuestaRs("Cantidad del medicamento actualizada exitosamente", true, 200, medicamento);
    }

    // --- Métodos privados de apoyo ---

    private Medicamento mapearAMedicamento(MedicamentoRq medicamentoRq) {
        Medicamento nuevo = new Medicamento();
        nuevo.setNombre(medicamentoRq.getNombre());
        nuevo.setDescripcion(medicamentoRq.getDescripcion());
        nuevo.setPresentacion(medicamentoRq.getPresentacion());
        nuevo.setFechaCompra(medicamentoRq.getFechaCompra());
        nuevo.setFechaVence(medicamentoRq.getFechaVence());
        nuevo.setCantidad(medicamentoRq.getCantidad());
        nuevo.setFechaCreacionRegistro(LocalDateTime.now());
        nuevo.setFechaModificacionRegistro(LocalDateTime.now());
        return nuevo;
    }

    private void validarFormulario(MedicamentoRq medicamentoRq) throws BadRequestException {
        if (medicamentoRq.getNombre() == null || medicamentoRq.getNombre().isBlank()) {
            throw new BadRequestException("El nombre es obligatorio");
        }
        if (medicamentoRq.getDescripcion() == null || medicamentoRq.getDescripcion().isBlank()) {
            throw new BadRequestException("La descripción es obligatoria");
        }
        if (medicamentoRq.getPresentacion() == null || medicamentoRq.getPresentacion().isBlank()) {
            throw new BadRequestException("La presentación es obligatoria");
        }
        if (medicamentoRq.getFechaCompra() == null) {
            throw new BadRequestException("La fecha de compra es obligatoria");
        }
        if (medicamentoRq.getFechaVence() == null) {
            throw new BadRequestException("La fecha de vencimiento es obligatoria");
        }
    }
}
