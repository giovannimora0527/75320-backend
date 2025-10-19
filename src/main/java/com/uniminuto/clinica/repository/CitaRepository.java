package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> { 

    List<Cita> findAllByOrderByFechaHoraDesc();

}
