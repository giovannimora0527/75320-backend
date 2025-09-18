package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecetaService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecetaApiController implements RecetaApi {

    private final RecetaService recetaService;

    public RecetaApiController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @Override
    public ResponseEntity<RespuestaRs> createReceta(Long citaId, Receta receta) throws BadRequestException {
        try {
            Receta nueva = recetaService.createReceta(
                    receta.getMedicamento().getId(), // primero medicamentoId (Integer)
                    citaId,                          // después citaId (Long)
                    receta.getDosis(),
                    receta.getIndicaciones()
            );

            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(201);
            respuesta.setMessage("Receta creada correctamente con id " + nueva.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (Exception e) {
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(400);
            respuesta.setMessage("No se pudo crear la receta: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<List<Receta>> getRecetasByCita(Long citaId) throws BadRequestException {
        List<Receta> recetas = recetaService.getRecetasByCita(citaId);
        if (recetas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recetas);
    }

    @Override
    public ResponseEntity<Receta> getRecetaById(Long id) throws BadRequestException {
        try {
            Receta receta = recetaService.getRecetaById(id);
            return ResponseEntity.ok(receta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
