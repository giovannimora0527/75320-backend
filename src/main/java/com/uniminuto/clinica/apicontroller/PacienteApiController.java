package com.uniminuto.clinica.apicontroller;

/**
 *
 * @author lmora
 */
import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PacienteApiController implements PacienteApi {

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
/**
     *
     * @author JulianLopez
     *
     * Nuevo método: listar pacientes en orden ascendente (más viejo -> más
     * joven)
     */
    @Override
    public ResponseEntity<List<Paciente>> listarPacientesPorEdadAsc() {
        return ResponseEntity.ok(pacienteService.encontrarPacientesOrdenadosPorFechaNacimientoAsc());
    }
}
