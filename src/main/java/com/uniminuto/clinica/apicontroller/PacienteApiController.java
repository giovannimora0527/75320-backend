package com.uniminuto.clinica.apicontroller;

<<<<<<< HEAD
import com.uniminuto.clinica.api.PacienteApi;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.apache.coyote.BadRequestException;


/**
 *
 * @author Andre
 */
=======
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
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c

@RestController
public class PacienteApiController implements PacienteApi {

    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<Paciente>> listarPacientes() {
<<<<<<< HEAD
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }
    
    //** sedundo api buscar por documento**//
    @Override
    public ResponseEntity<Paciente> buscarPorDocumento(String numeroDocumento) 
        throws BadRequestException {
        return ResponseEntity.ok(pacienteService.buscarPorNumeroDocumento(numeroDocumento));
    }
}
=======
        return ResponseEntity.ok(pacienteService.encontrarTodosLosPacientes());
    }

    @Override
    public ResponseEntity<Paciente> buscarPacienteXIdentificacion(String numeroDocumento)
            throws BadRequestException {
        return ResponseEntity.ok(pacienteService.buscarPacientePorDocumento(numeroDocumento));
    }
}
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
