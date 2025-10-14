package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "http://localhost:4200") // ✅ Permite peticiones desde Angular
public class MedicamentoApiController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // ✅ GET → Listar todos
    @GetMapping("/listar")
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll();
    }

    // ✅ POST → Crear medicamento
    @PostMapping("/crear")
    public Medicamento crearMedicamento(@RequestBody Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    // ✅ PUT → Actualizar medicamento
    @PutMapping("/actualizar/{id}")
    public Medicamento actualizarMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        Medicamento existente = medicamentoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(medicamento.getNombre());
            existente.setDescripcion(medicamento.getDescripcion());
            existente.setPrecio(medicamento.getPrecio());
            existente.setStock(medicamento.getStock());
            return medicamentoRepository.save(existente);
        }
        return null;
    }

    // ✅ DELETE → Eliminar medicamento
    @DeleteMapping("/eliminar/{id}")
    public void eliminarMedicamento(@PathVariable Long id) {
        medicamentoRepository.deleteById(id);
    }
}




