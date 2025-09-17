package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> { 

    @Query("SELECT c FROM Cita c ORDER BY c.fechaHora DESC")
    List<Cita> findAllOrderByFechaHoraDesc();

}
