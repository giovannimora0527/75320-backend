package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio JPA para la entidad Cita.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    /**
     * Obtiene todas las citas ordenadas por fecha y hora descendente.
     * @return lista de citas ordenadas por fecha y hora.
     */
    List<Cita> findAllByOrderByFechaHoraDesc();

    /**
     * Obtiene las citas de un médico en un rango de fechas.
     * @param medico el médico asociado.
     * @param fechaIni fecha de inicio del rango.
     * @param fechaFin fecha de fin del rango.
     * @return lista de citas del médico.
     */
    List<Cita> findByMedicoAndFechaHoraBetween(
            Medico medico, LocalDateTime fechaIni, LocalDateTime fechaFin);

    /**
     * Obtiene las citas de un paciente en un rango de fechas.
     * @param paciente el paciente asociado.
     * @param fechaIni fecha de inicio del rango.
     * @param fechaFin fecha de fin del rango.
     * @return lista de citas del paciente.
     */
    List<Cita> findByPacienteAndFechaHoraBetween(
            Paciente paciente, LocalDateTime fechaIni, LocalDateTime fechaFin);

    /**
     * Obtiene todas las citas de un paciente, ordenadas por fecha y hora descendente.
     * @param paciente el paciente asociado.
     * @return lista de citas ordenadas.
     */
    List<Cita> findByPacienteOrderByFechaHoraDesc(Paciente paciente);

    /**
     * Obtiene todas las citas de un paciente.
     * @param paciente el paciente asociado.
     * @return lista de citas del paciente.
     */
    List<Cita> findByPaciente(Paciente paciente);
}
