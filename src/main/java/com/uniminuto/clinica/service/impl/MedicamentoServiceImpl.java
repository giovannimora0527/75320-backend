package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.service.MedicamentoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> listarAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public RespuestaRs guardarMedicamento(MedicamentoRq medicamento) throws BadRequestException {
        this.validadorCampos(medicamento);

        Optional<Medicamento> optMedic = this.medicamentoRepository
                .findByNombreAndPresentacion(medicamento.getNombre(), medicamento.getPresentacion());

        if (optMedic.isPresent()) {
            throw new BadRequestException("Ya existe un medicamento con ese nombre y presentación");
        }

        Medicamento objGuardar = this.convertirAMedicamentoClass(medicamento);
        this.medicamentoRepository.save(objGuardar);

        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se guardó el medicamento satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }

    @Override
    public Medicamento buscarPorId(Integer id) throws BadRequestException {
        Optional<Medicamento> optMedicamento = medicamentoRepository.findById(id);
        if (!optMedicamento.isPresent()) {
            throw new BadRequestException("No se encuentra el medicamento");
        }
        return optMedicamento.get();
    }

    @Override
    public RespuestaRs actualizarMedicamento(MedicamentoRq medicamento) throws BadRequestException {
        this.validadorCampos(medicamento);

        Medicamento medicamentoUpdate = this.buscarPorId(medicamento.getId());

        // Validar que no exista otro medicamento con el mismo nombre y presentación
        Optional<Medicamento> optMedic = this.medicamentoRepository
                .findByNombreAndPresentacionAndIdNot(
                        medicamento.getNombre(),
                        medicamento.getPresentacion(),
                        medicamento.getId()
                );

        if (optMedic.isPresent()) {
            throw new BadRequestException("Ya existe otro medicamento con ese nombre y presentación");
        }

        // Actualizar campos
        medicamentoUpdate.setPresentacion(
                medicamento.getPresentacion() == null ? medicamentoUpdate.getPresentacion() : medicamento.getPresentacion());
        medicamentoUpdate.setDescripcion(
                medicamento.getDescripcion() == null ? medicamentoUpdate.getDescripcion() : medicamento.getDescripcion());
        medicamentoUpdate.setNombre(
                medicamento.getNombre() == null ? medicamentoUpdate.getNombre() : medicamento.getNombre());
        medicamentoUpdate.setFechaCompra(
                medicamento.getFechaCompra() == null ? medicamentoUpdate.getFechaCompra() : medicamento.getFechaCompra());
        medicamentoUpdate.setFechaVence(
                medicamento.getFechaVence() == null ? medicamentoUpdate.getFechaVence() : medicamento.getFechaVence());
        medicamentoUpdate.setFechaModificacionRegistro(LocalDateTime.now());

        this.medicamentoRepository.save(medicamentoUpdate);

        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se actualizó el medicamento satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }

    private void validadorCampos(MedicamentoRq medicamento) throws BadRequestException {
        if (medicamento.getDescripcion() == null || medicamento.getDescripcion().isBlank()) {
            throw new BadRequestException("Descripción es obligatoria");
        }
        if (medicamento.getNombre() == null || medicamento.getNombre().isBlank()) {
            throw new BadRequestException("Nombre del medicamento es obligatorio");
        }
        if (medicamento.getPresentacion() == null || medicamento.getPresentacion().isBlank()) {
            throw new BadRequestException("Presentación es obligatoria");
        }
        if (medicamento.getFechaCompra() == null) {
            throw new BadRequestException("Fecha de compra es obligatoria");
        }
        if (medicamento.getFechaVence() == null) {
            throw new BadRequestException("Fecha de vencimiento es obligatoria");
        }
    }

    private Medicamento convertirAMedicamentoClass(MedicamentoRq medicamentoRq) {
        Medicamento nuevo = new Medicamento();
        nuevo.setDescripcion(medicamentoRq.getDescripcion());
        nuevo.setNombre(medicamentoRq.getNombre());
        nuevo.setPresentacion(medicamentoRq.getPresentacion());
        nuevo.setFechaCompra(medicamentoRq.getFechaCompra());
        nuevo.setFechaVence(medicamentoRq.getFechaVence());
        nuevo.setFechaCreacionRegistro(LocalDateTime.now());
        return nuevo;
    }
}