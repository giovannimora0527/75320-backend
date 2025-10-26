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
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
    public RespuestaRs guardarReceta(RecetaRq recetaRq)throws BadRequestException {
        /**
         * Guarda el objeto.
         */
        Receta receta = new Receta();

        Cita cita = new Cita();
        cita.setId(recetaRq.getCita());
        receta.setCita(cita);

        Medicamento medicamento = new Medicamento();
        medicamento.setId(recetaRq.getMedicamento());
        receta.setMedicamento(medicamento);

        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        receta.setFechaCreacionRegistro(LocalDateTime.now());
        this.recetaRepository.save(receta);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Receta guardada con éxito.");
        return rta;
    }

    /**
     * Convertir objeto.
     */

    @Override
    public RespuestaRs actualizarReceta(RecetaRq recetaRq) throws BadRequestException {
        Optional<Receta> recetaExiste = this.recetaRepository.findById(recetaRq.getId());
        if (recetaExiste.isEmpty()) {
            throw new BadRequestException("La receta no existe y no se puede actualizar.");
        }

        Receta receta = recetaExiste.get();

        Cita cita = new Cita();
        cita.setId(recetaRq.getCita());
        receta.setCita(cita);

        Medicamento medicamento = new Medicamento();
        medicamento.setId(recetaRq.getMedicamento());
        receta.setMedicamento(medicamento);

        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        this.recetaRepository.save(receta);
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("Reta actualizada con éxito.");
        rta.setStatus(200);
        return rta;
    }
}
