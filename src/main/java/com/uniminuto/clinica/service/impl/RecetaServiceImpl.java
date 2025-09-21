package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Implementacion del servicio de receta
*/
/**
* @author Anderson
*/

@Service
public class RecetaServiceImpl implements RecetaService{
    /**
     * Inyectar el repositorio.
     */
    @Autowired
    private RecetaRepository recetaRepository;
    /**
     * Implementar metodos.
     */
    @Override
    public List<Receta> listarRecetas(){
        return recetaRepository.findAll();
    }
    /**
     * Metodo guardar.
     */
    @Override
    public RespuestaRs guardarReceta(RecetaRq receta)throws BadRequestException {
        this.validadorCampos(receta);
    /**
    * Guarda el objeto.
    */
    Receta objGuardar =this.convertirRecetaClass(receta);
    this.recetaRepository.save(objGuardar);
    RespuestaRs rta = new RespuestaRs();
    rta.setMessage("Receta guardada exitosamente");
    rta.setStatus(200);
    return rta;
    }  
    /**
    * Validador de campos.
    */
    private void validadorCampos(RecetaRq receta)throws BadRequestException{
        if (receta.getCitaId() == null) {
            throw new BadRequestException("El ID de la cita es obligatorio");
        }
            if (receta.getMedicamentoId() == null)  {
            throw new BadRequestException("El Id del medicamento es obligatorio");
        }

        if (receta.getDosis() == null|| receta.getDosis().isBlank()||
                receta.getDosis().isEmpty()) {
        throw new BadRequestException("La docis es obligatorias");
        }

        if (receta.getIndicaciones() == null || receta.getIndicaciones().isBlank()||
                receta.getIndicaciones().isEmpty()) {
        throw new BadRequestException("Las indicaciones son obligatorias");
        }
    }
    /**
    * Convertir objeto.
    */
    private Receta convertirRecetaClass(RecetaRq recetaRq){
        Receta nuevo = new Receta();
    /**
    * Cita.
    */
    Cita cita = new Cita();
    cita.setId(recetaRq.getCitaId());
    nuevo.setCita(cita);
    /**
    * Medicamento.
    */
    Medicamento medicamento = new Medicamento();
    medicamento.setId(recetaRq.getMedicamentoId());
    nuevo.setMedicamento(medicamento);    
    /**
    * Otros campos.
    */
    nuevo.setDosis(recetaRq.getDosis());
    nuevo.setIndicaciones(recetaRq.getIndicaciones());
    nuevo.setFechaCreacionRegistro(LocalDateTime.now());      
    return nuevo;
}
}