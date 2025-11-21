package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import java.time.LocalDateTime;
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

    // Alternativa usando método derivado por propiedad anidada
    List<Receta> findAllByOrderByCita_FechaHoraDesc();
    
    // Buscar recetas por ID de cita
    List<Receta> findByCita_Id(Integer citaId);

    // Buscar recetas por ID de medicamento
    List<Receta> findByMedicamento_Id(Integer medicamentoId);

    // Buscar recetas entre fechas
    List<Receta> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

}
