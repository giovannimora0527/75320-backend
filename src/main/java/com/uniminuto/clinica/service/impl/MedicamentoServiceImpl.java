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
                .findByNombre(medicamento.getNombre());
        if (optMedic.isPresent()) {
            throw new BadRequestException("El medicamento ya existe");
        }
        Medicamento objGuardar = this.convertirAMedicamentoClass(medicamento);
        this.medicamentoRepository.save(objGuardar);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se guardo el medicamento satisfactoriamente");
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
        Medicamento medicamentoUpdate = this.buscarPorId(medicamento.getId());
        Optional<Medicamento> optMedic = this.medicamentoRepository
                .findByNombre(medicamento.getNombre());
        if (optMedic.isPresent()) {
            throw new BadRequestException("El medicamento ya existe y no se puede actualizar");
        }
        medicamentoUpdate.setPresentacion(medicamento.getPresentacion() == null? medicamentoUpdate.getPresentacion() : medicamento.getPresentacion());
        medicamentoUpdate.setDescripcion(medicamento.getDescripcion() == null? medicamentoUpdate.getDescripcion() : medicamento.getDescripcion());
        medicamentoUpdate.setNombre(medicamento.getNombre() == null? medicamentoUpdate.getNombre() : medicamento.getNombre());
        medicamentoUpdate.setFechaCompra(medicamento.getFechaCmpra() == null? medicamentoUpdate.getFechaCompra() : medicamento.getFechaCmpra());
        medicamentoUpdate.setFechaVence(medicamento.getFechaVence() == null? medicamentoUpdate.getFechaVence() : medicamento.getFechaVence());
        medicamentoUpdate.setFechaModificacionRegistro(LocalDateTime.now());
        this.medicamentoRepository.save(medicamentoUpdate);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se actualizo el medicamento satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }

    private void validadorCampos(MedicamentoRq medicamento) throws BadRequestException {
        if (medicamento.getDescripcion() == null || medicamento.getDescripcion().isBlank() ||
                medicamento.getDescripcion().isEmpty()) {
            throw new BadRequestException("Descripcion es obligatoria");
        }
        if (medicamento.getNombre() == null || medicamento.getNombre().isBlank() ||
                medicamento.getNombre().isEmpty()) {
            throw new BadRequestException("Nombre del medicamento es obligatorio");
        }
        if (medicamento.getPresentacion() == null || medicamento.getPresentacion().isBlank() ||
                medicamento.getPresentacion().isEmpty()) {
            throw new BadRequestException("Presentación es obligatoria");
        }
        if (medicamento.getFechaCmpra() == null) {
            throw new BadRequestException("Fecha de compra es obligarorio es obligatoria");
        }
        if (medicamento.getFechaVence() == null) {
            throw new BadRequestException("Fecha vencimiento es obligatoria");
        }
    }


    private Medicamento convertirAMedicamentoClass(MedicamentoRq medicamentoRq) {
        Medicamento nuevo = new Medicamento();
        nuevo.setDescripcion(medicamentoRq.getDescripcion());
        nuevo.setNombre(medicamentoRq.getNombre());
        nuevo.setPresentacion(medicamentoRq.getPresentacion());
        nuevo.setFechaCompra(medicamentoRq.getFechaCmpra());
        nuevo.setFechaVence(medicamentoRq.getFechaVence());
        nuevo.setFechaCreacionRegistro(LocalDateTime.now());
        return nuevo;
    }
}
