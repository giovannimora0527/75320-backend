package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
/**
 *
 * @author Andre
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
   Optional<Paciente> findByNumeroDocumento(String numeroDocumento);
=======
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
    
    Optional<Paciente> findByNumeroDocumento(String documento);
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
}
