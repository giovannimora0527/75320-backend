package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
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




