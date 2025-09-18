package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.CitaRs;
import com.uniminuto.clinica.model.RecetaRs;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/receta")
public interface RecetaApi {

    // ✅ Ahora citaId llega en el body
    @RequestMapping(
            value = "/agregarReceta",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> createReceta(
            @RequestBody CitaRs request)
            throws BadRequestException;

    @RequestMapping(
           value = "/listarRecetas",
           produces = {"application/json"},
           method = RequestMethod.GET
       )
        ResponseEntity<List<RecetaRs>> getRecetasOrdenadas();
    
}
