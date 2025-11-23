package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.MedicamentoApi;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.MedicamentoService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST que implementa la API de Medicamentos.
 * 
 * Endpoints:
 *  - GET  /medicamento/listar → Lista todos los medicamentos.
 *  - GET  /medicamento/buscar → Busca un medicamento por nombre.
 *  - POST /medicamento/guardar → Guarda un nuevo medicamento.
 *  - PUT  /medicamento/actualizar/{id} → Actualiza un medicamento existente.
 *  - DELETE /medicamento/eliminar/{id} → Elimina un medicamento.
 *  - PATCH /medicamento/actualizar-cantidad/{id} → Actualiza solo la cantidad disponible.
 * 
 * @author
 */
@RestController
public class MedicamentoApiController implements MedicamentoApi {

    private final MedicamentoService medicamentoService;

    // ✅ Constructor para inyección de dependencias (reemplaza @Autowired)
    public MedicamentoApiController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    /**
     * Lista todos los medicamentos.
     */
    @Override
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        try {
            List<Medicamento> medicamentos = medicamentoService.listarMedicamentos();
            return ResponseEntity.ok(medicamentos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Busca un medicamento por su nombre.
     */
    @Override
    public ResponseEntity<Medicamento> buscarPorNombre(String nombre) throws BadRequestException {
        return ResponseEntity.ok(medicamentoService.buscarPorNombre(nombre));
    }

    /**
     * Guarda un nuevo medicamento.
     */
    @Override
    public ResponseEntity<RespuestaRs> guardarMedicamento(MedicamentoRq medicamentoRq) throws BadRequestException {
        return ResponseEntity.ok(medicamentoService.guardarMedicamento(medicamentoRq));
    }

    /**
     * Actualiza un medicamento existente.
     */
    @Override
    public ResponseEntity<RespuestaRs> actualizarMedicamento(Integer id, MedicamentoRq medicamentoRq)
            throws BadRequestException {
        return ResponseEntity.ok(medicamentoService.actualizarMedicamento(id, medicamentoRq));
    }

    /**
     * Elimina un medicamento por su ID.
     */
    @Override
    public ResponseEntity<RespuestaRs> eliminarMedicamento(Integer id) throws BadRequestException {
        return ResponseEntity.ok(medicamentoService.eliminarMedicamento(id));
    }

    /**
     * Actualiza solo la cantidad disponible de un medicamento.
     */
    @Override
    public ResponseEntity<RespuestaRs> actualizarCantidad(Integer id, Integer cantidad)
            throws BadRequestException {
        return ResponseEntity.ok(medicamentoService.actualizarCantidad(id, cantidad));
    }
}
