package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

    Optional<Paciente> findByNumeroDocumento(String documento);
    boolean existsByNumeroDocumento(String numeroDocumento);

    Optional<Paciente> findByUsuarioId(Integer usuarioId);

    List<Paciente> findAllByOrderByFechaNacimientoAsc();

}
