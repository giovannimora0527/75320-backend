package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta,Long> {
    List<Receta> findAllByOrderByFechaCreacionRegistroDesc();
}
=======
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    // Aquí se podrían agregar consultas personalizadas si se necesitan
}



>>>>>>> origin/Mariana_976621Castillo
