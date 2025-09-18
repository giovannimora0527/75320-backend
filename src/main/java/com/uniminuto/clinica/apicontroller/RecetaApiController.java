package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.CitaRs;

import com.uniminuto.clinica.model.RecetaRs;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecetaService;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecetaApiController implements RecetaApi {

    private final RecetaService recetaService;

    public RecetaApiController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @Override
    public ResponseEntity<RespuestaRs> createReceta(CitaRs request) throws BadRequestException {
        try {
            Long citaId = request.getCitaId();
            Receta receta = request.getReceta();

            Receta nueva = recetaService.createReceta(
                    receta.getMedicamento().getId(),
                    citaId,
                    receta.getDosis(),
                    receta.getIndicaciones()
            );

            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(200);
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
    public ResponseEntity<List<RecetaRs>> getRecetasOrdenadas() {
        // Obtener la lista de recetas de la base de datos
        List<Receta> recetas = recetaService.obtenerRecetasOrdenadasPorFechaDesc();

        // Mapear cada entidad Receta a RecetaRs
        List<RecetaRs> respuesta = recetas.stream().map(r -> {
            RecetaRs rs = new RecetaRs();
            rs.setId(r.getId().intValue());
            rs.setCitaId(r.getCita().getId().intValue());
            rs.setMedicamentoId(r.getMedicamento().getId());
            rs.setDosis(r.getDosis());
            rs.setIndicaciones(r.getIndicaciones());
            rs.setFechaCreacionRegistro(r.getFechaCreacionRegistro());
            return rs;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }
}
