package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
<<<<<<< HEAD
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findAllByOrderByFechaHoraDesc();

    List<Cita> findByMedicoAndFechaHoraBetween(Medico medico, LocalDateTime start, LocalDateTime end);

    List<Cita> findByPacienteAndFechaHoraBetween(Paciente paciente, LocalDateTime start, LocalDateTime end);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findAllByOrderByFechaCitaDesc();
}




>>>>>>> origin/Mariana_976621Castillo
