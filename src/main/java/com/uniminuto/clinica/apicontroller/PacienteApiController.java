package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nicolas
 */
@RestController
public class PacienteApiController implements PacienteApi {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Ahora implementamos directamente los métodos del servicio
    @Override
    public ResponseEntity<List<Paciente>> listarPaciente() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @Override
    public ResponseEntity<Paciente> buscarPacienteXNumero_documento(String numero_documento) throws BadRequestException {
        try {
            Optional<Paciente> pacienteOpt = pacienteRepository.findByNumeroDocumento(numero_documento);
            Paciente paciente = pacienteOpt.orElseThrow(() -> new BadRequestException("Paciente no encontrado"));
            return ResponseEntity.ok(paciente);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Número de documento inválido");
        }
    }
    @Override
    public ResponseEntity<List<Paciente>> listarPacientesPorfechaNacimiento() {
        return ResponseEntity.ok(pacienteRepository.findAllByOrderByFechaNacimientoAsc());
    }
}
