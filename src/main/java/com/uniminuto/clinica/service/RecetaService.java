
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author Andre
 */


public interface RecetaService {
    
    List<Receta> listarRecetas();
    
    RespuestaRs guardarReceta(RecetaRq receta) throws BadRequestException;
}
