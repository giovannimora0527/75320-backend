package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio JPA para la entidad Receta.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    /**
     * Obtiene todas las recetas ordenadas por la fecha y hora de la cita (descendente).
     */
    List<Receta> findAllByOrderByCita_FechaHoraDesc();

    /**
     * Busca recetas por ID de cita.
     * @param citaId identificador de la cita.
     */
    List<Receta> findByCita_Id(Integer citaId);

    /**
     * Busca recetas por ID de medicamento.
     * @param medicamentoId identificador del medicamento.
     */
    List<Receta> findByMedicamento_Id(Integer medicamentoId);

    /**
     * Busca recetas por un rango de fechas.
     * @param inicio fecha y hora inicial.
     * @param fin fecha y hora final.
     */
    List<Receta> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Busca recetas por cita y medicamento específicos.
     * @param cita objeto cita.
     * @param medicamento objeto medicamento.
     */
    List<Receta> findByCitaAndMedicamento(Cita cita, Medicamento medicamento);
}
