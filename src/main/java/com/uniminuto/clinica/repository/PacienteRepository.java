package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
    
      // Buscar un paciente por número de documento
    Optional<Paciente> findByNumeroDocumento(String documento);
     // Listar todos los pacientes ordenados por fecha de nacimiento (mayor a menor)
    List<Paciente> findAllByOrderByFechaNacimientoAsc(); 
}