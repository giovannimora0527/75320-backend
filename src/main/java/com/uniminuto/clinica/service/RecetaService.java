package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
<<<<<<< HEAD
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface RecetaService {
    List<Receta> listarRecetasOrdenadas();

    RespuestaRs guardarReceta(RecetaRq recetaRq) throws BadRequestException;
}
=======
import com.uniminuto.clinica.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    // Crear receta
    public Receta crearReceta(Receta receta) {
        return recetaRepository.save(receta); // La fecha se llenará automáticamente
    }

    // Listar todas las recetas
    public List<Receta> listarRecetas() {
        return recetaRepository.findAll();
    }
}




>>>>>>> origin/Mariana_976621Castillo
