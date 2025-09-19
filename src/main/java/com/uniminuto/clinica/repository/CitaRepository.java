package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findAllByOrderByFechaHoraDesc();

    List<Cita> findByFechaHoraBetweenOrderByFechaHoraDesc(LocalDateTime desde, LocalDateTime hasta);
}










