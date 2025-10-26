package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
    /**
     * Metodo buscar por numero de documento de paciente
     */
    Optional<Paciente> findByNumeroDocumento(String documento);
    /**
     * Metodo listar por paciente por fecha de nacimiento
     */
    List<Paciente> findAllByOrderByFechaNacimientoAsc();
}
