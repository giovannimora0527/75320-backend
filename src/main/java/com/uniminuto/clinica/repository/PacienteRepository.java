package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
    
    Optional<Paciente> findByNumeroDocumento(String documento);
    
    List<Paciente> findAllByOrderByFechaNacimientoAsc();
}
