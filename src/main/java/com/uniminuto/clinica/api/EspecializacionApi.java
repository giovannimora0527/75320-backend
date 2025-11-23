package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Especializacion;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;           
import io.swagger.v3.oas.annotations.tags.Tag;            
import io.swagger.v3.oas.annotations.media.Content;       
import io.swagger.v3.oas.annotations.media.Schema;        
import io.swagger.v3.oas.annotations.responses.ApiResponse; 

@CrossOrigin(origins = "*")
@Tag(name = "Especialización", description = "Endpoints para gestión de especializaciones")  
@RequestMapping("/especializacion")
public interface EspecializacionApi {
    
    @Operation(
            summary = "Listar especializaciones",
            description = "Retorna todas las especializaciones registradas.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido correctamente",
                            content = @Content(schema = @Schema(implementation = Especializacion.class))
                    )
            }
    )
    @RequestMapping(
            value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET   // ← CONSUMES QUITADO
    )
    ResponseEntity<List<Especializacion>> listarEspecializaciones();
    
    
    @Operation(
            summary = "Buscar especialización por código",
            description = "Consulta una especialización usando su código.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Especialización encontrada",
                            content = @Content(schema = @Schema(implementation = Especializacion.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Código inválido",
                            content = @Content(schema = @Schema(implementation = Especializacion.class))
                    )
            }
    )
    @RequestMapping(
            value = "/buscar-por-codigo",
            produces = {"application/json"},
            method = RequestMethod.GET  // ← CONSUMES QUITADO
    )
    ResponseEntity<Especializacion> buscarPorCodigo(
      @RequestParam String codigo
    ) throws BadRequestException;
}
