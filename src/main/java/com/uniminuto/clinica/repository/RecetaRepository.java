package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findAllByOrderByFechaCreacionRegistroDesc();
}










