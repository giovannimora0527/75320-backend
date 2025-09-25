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

    // Inyección de dependencia del repositorio para interactuar con la base de datos.
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // Método para obtener una lista de todos los medicamentos.
    @Override
    public List<Medicamento> listarAllMedicamentos() {
        // Delega la llamada al método findAll() del repositorio.
        return medicamentoRepository.findAll();
    }

    @Override
    public RespuestaRs guardarMedicamento(MedicamentoRq medicamento) throws BadRequestException {
        
        // Paso 1: Valida que los campos requeridos no estén vacíos.
        this.validadorCampos(medicamento);
        // Paso 2: Verifica si ya existe un medicamento con el mismo nombre.
        Optional<Medicamento> optMedic = this.medicamentoRepository
                .findByNombre(medicamento.getNombre());
        if (optMedic.isPresent()) {
            throw new BadRequestException("El medicamento ya existe");
        }
        // Paso 3: Convierte el objeto DTO (MedicamentoRq) a la entidad Medicamento.
        Medicamento objGuardar = this.convertirAMedicamentoClass(medicamento);
        // Paso 4: Guarda la entidad en la base de datos.
        this.medicamentoRepository.save(objGuardar);
        // Paso 5: Crea y devuelve una respuesta exitosa.
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se guardo el medicamento satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }

    @Override
    public Medicamento buscarPorId(Integer id) throws BadRequestException {
        // Busca por ID y maneja el caso de que no se encuentre.
        Optional<Medicamento> optMedicamento = medicamentoRepository.findById(id);
        if (!optMedicamento.isPresent()) {
            throw new BadRequestException("No se encuentra el medicamento");
        }
        return optMedicamento.get();
    }

    @Override
    public RespuestaRs actualizarMedicamento(MedicamentoRq medicamento) throws BadRequestException {
        // Busca el medicamento por su ID para verificar que existe.
        Medicamento medicamentoUpdate = this.buscarPorId(medicamento.getId());
        // Verifica si se está intentando cambiar el nombre a uno que ya existe.
        Optional<Medicamento> optMedic = this.medicamentoRepository
                .findByNombre(medicamento.getNombre());
        if (optMedic.isPresent()) {
            throw new BadRequestException("El medicamento ya existe y no se puede actualizar");
        }
        // Actualiza cada campo solo si el valor en el DTO no es nulo.
        medicamentoUpdate.setPresentacion(medicamento.getPresentacion() == null? medicamentoUpdate.getPresentacion() : medicamento.getPresentacion());
        medicamentoUpdate.setDescripcion(medicamento.getDescripcion() == null? medicamentoUpdate.getDescripcion() : medicamento.getDescripcion());
        medicamentoUpdate.setNombre(medicamento.getNombre() == null? medicamentoUpdate.getNombre() : medicamento.getNombre());
        
        // Revisa si el campo en el DTO es 'null' y si no lo es, asigna el valor.
        medicamentoUpdate.setFechaCompra(medicamento.getFechaCmpra() == null? medicamentoUpdate.getFechaCompra() : medicamento.getFechaCmpra());
        medicamentoUpdate.setFechaVence(medicamento.getFechaVence() == null? medicamentoUpdate.getFechaVence() : medicamento.getFechaVence());
        
        // Actualiza la marca de tiempo de modificación.
        medicamentoUpdate.setFechaModificacionRegistro(LocalDateTime.now());
        
        // Guarda la entidad actualizada en la base de datos.
        this.medicamentoRepository.save(medicamentoUpdate);
        
        // Prepara la respuesta de éxito.
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Se actualizo el medicamento satisfactoriamente");
        rta.setStatus(200);
        return rta;
    }

    // Método privado para validar los campos del DTO antes de guardar o actualizar.
    private void validadorCampos(MedicamentoRq medicamento) throws BadRequestException {
        
        // Lanza una excepción si la descripción está vacía o nula.
        if (medicamento.getDescripcion() == null || medicamento.getDescripcion().isBlank() ||
                medicamento.getDescripcion().isEmpty()) {
            throw new BadRequestException("Descripcion es obligatoria");
        }
        
        // Lanza una excepción si el nombre está vacío o nulo.
        if (medicamento.getNombre() == null || medicamento.getNombre().isBlank() ||
                medicamento.getNombre().isEmpty()) {
            throw new BadRequestException("Nombre del medicamento es obligatorio");
        }
        
        // Lanza una excepción si la presentación está vacía o nula.
        if (medicamento.getPresentacion() == null || medicamento.getPresentacion().isBlank() ||
                medicamento.getPresentacion().isEmpty()) {
            throw new BadRequestException("Presentación es obligatoria");
        }
        
        // Lanza una excepción si la fecha de compra es nula.
        if (medicamento.getFechaCmpra() == null) {
            throw new BadRequestException("Fecha de compra es obligarorio es obligatoria");
        }
        
        // Lanza una excepción si la fecha de vencimiento es nula.
        if (medicamento.getFechaVence() == null) {
            throw new BadRequestException("Fecha vencimiento es obligatoria");
        }
    }

    // Método privado para convertir el DTO a la entidad.
    private Medicamento convertirAMedicamentoClass(MedicamentoRq medicamentoRq) {
        
        // Crea una nueva instancia de la entidad Medicamento.
        Medicamento nuevo = new Medicamento();
        
        // Copia los datos del DTO a la entidad.
        nuevo.setDescripcion(medicamentoRq.getDescripcion());
        nuevo.setNombre(medicamentoRq.getNombre());
        nuevo.setPresentacion(medicamentoRq.getPresentacion());
        nuevo.setFechaCompra(medicamentoRq.getFechaCmpra());
        nuevo.setFechaVence(medicamentoRq.getFechaVence());
        
        // Establece la fecha de creación del registro.
        nuevo.setFechaCreacionRegistro(LocalDateTime.now());
        return nuevo;
    }
}
