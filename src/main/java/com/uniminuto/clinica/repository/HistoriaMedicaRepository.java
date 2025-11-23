package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.HistoriaMedica;
import com.uniminuto.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para la entidad HistoriaMedica.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface HistoriaMedicaRepository extends JpaRepository<HistoriaMedica, Integer> {

    /**
     * Busca historias médicas por paciente.
     * @param pacienteId identificador del paciente.
     * @return lista de historias médicas del paciente.
     */
    List<HistoriaMedica> findByPaciente_Id(Integer pacienteId);

    /**
     * Busca historias médicas por médico.
     * @param medicoId identificador del médico.
     * @return lista de historias médicas del médico.
     */
    List<HistoriaMedica> findByMedico_Id(Integer medicoId);

    /**
     * Busca historias médicas por paciente.
     * @param paciente objeto paciente.
     * @return lista de historias médicas del paciente.
     */
    List<HistoriaMedica> findByPaciente(Paciente paciente);
}

