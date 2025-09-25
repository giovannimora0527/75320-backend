package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    
    //Busca por Fecha de creacion y lor organiza de forma ddescendente.
    List<Receta> findAllByOrderByFechaCreacionRegistroDesc();
}

