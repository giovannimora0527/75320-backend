package com.uniminuto.clinica.apicontroller;

/**
 *
 * @author lmora
 */
import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.PacienteService;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PacienteApiController implements PacienteApi {
    @Override
    public ResponseEntity<RespuestaRs> guardarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        return ResponseEntity.ok(this.pacienteService.guardarPaciente(pacienteRq));
    }

    @Override
    public ResponseEntity<RespuestaRs> actualizarPaciente(PacienteRq pacienteRq) throws BadRequestException {
        return ResponseEntity.ok(this.pacienteService.actualizarPaciente(pacienteRq));
    }

    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.encontrarTodosLosPacientes());
    }

    @Override
    public ResponseEntity<Paciente> buscarPacienteXIdentificacion(String numeroDocumento)
            throws BadRequestException {
        return ResponseEntity.ok(pacienteService.buscarPacientePorDocumento(numeroDocumento));
    }

    @Override
    public ResponseEntity<List<Paciente>> listarPacientesPorEdad() {
        return ResponseEntity.ok(this.pacienteService.listarPacientesPorEdad());
    }

}
