package com.uniminuto.clinica.apicontroller;

<<<<<<< HEAD
/**
 *
 * @author lmora
 */
import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;

=======
import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.service.PacienteService;
import com.uniminuto.clinica.service.UsuarioService;
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
/**
 *
 * @author Julian
 */
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
@RestController
public class PacienteApiController implements PacienteApi {

    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<Paciente>> listarPacientes() {
<<<<<<< HEAD
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
=======
        return ResponseEntity.ok(this.pacienteService.encontrarTodosLosPacientes());
    }

    @Override
    public ResponseEntity<Paciente> buscarPacienteXdocumento(String documento) throws BadRequestException {
        return ResponseEntity.ok(this.pacienteService.encontrarPacientePorDocumento(documento));
    }

>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
}
