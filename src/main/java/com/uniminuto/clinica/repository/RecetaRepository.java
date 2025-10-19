package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author PC
 */
@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    
    @Query("SELECT r FROM Receta r JOIN r.cita c ORDER BY c.fechaHora DESC")
    List<Receta> findAllOrderByCitaFechaHoraDesc();

    // Alternativa usando método derivado por propiedad anidada
    List<Receta> findAllByOrderByCita_FechaHoraDesc();

}
