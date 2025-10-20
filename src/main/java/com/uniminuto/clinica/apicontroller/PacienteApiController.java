package com.uniminuto.clinica.apicontroller;

/**
 *
 * @author lmora
 */
import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;

import java.util.List;
import java.util.Optional;

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
    @Override
    public ResponseEntity<List<Paciente>> listaPacienteFechaASC(){
       return ResponseEntity.ok(pacienteService.encontrarPacientesFNacimientoDesc());
    }

    @Override
    public ResponseEntity<List<Paciente>> buscarPacientePorGenero(String genero)
    {
        return ResponseEntity.ok(pacienteService.buscarPacientePorGenero(genero));
    }

    @Override
    public ResponseEntity<Paciente> obtenerPorIdPaciente(Integer id) throws BadRequestException {
        return ResponseEntity.ok(
                pacienteService.buscarPorIdPaciente(id)
                        .orElseThrow(() -> new BadRequestException("Paciente no encontrado con id=" + id))
        );
    }

    @Override
    public ResponseEntity<Paciente> crearPaciente(Paciente paciente) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.crearPaciente(paciente));
    }

    @Override
    public ResponseEntity<Paciente> actualizarPaciente(Integer id, Paciente paciente) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, paciente));
    }

    @Override
    public ResponseEntity<Void> eliminarPaciente(Integer id) throws BadRequestException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }


}
