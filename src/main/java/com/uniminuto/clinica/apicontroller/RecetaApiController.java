package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecetaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author PC
 */
@RestController
public class RecetaApiController implements RecetaApi {
    
    private final RecetaService recetaService;

    // Constructor manual (reemplaza @RequiredArgsConstructor)
    public RecetaApiController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarReceta(RecetaRq recetaRq) {
        try {
            return ResponseEntity.ok(recetaService.guardarReceta(recetaRq));
        } catch (Exception e) {
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(400);
            respuesta.setMessage("Error al crear la receta");
            return ResponseEntity.badRequest().body(respuesta);
        }
    }
    
    @Override
    public ResponseEntity<List<Receta>> listarRecetas() {
        return ResponseEntity.ok(recetaService.listarRecetas());
    }
}
