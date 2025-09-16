package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
        /**
     * Busca todas las citas y las ordena por fecha y hora en orden descendente.
     * Spring Data JPA construye la consulta automáticamente a partir del nombre del método.
     * @return Una lista de citas ordenadas de la más reciente a la más antigua.
     */
    List<Cita> findAllByOrderByFechaHoraDesc();
}