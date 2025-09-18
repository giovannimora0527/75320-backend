package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
    
    //busca un Paciente en la base de datos por su número de documento.
    Optional<Paciente> findByNumeroDocumento(String documento);
    
    //lista de todos los pacientes por su fecha de nacimiento
    List<Paciente> findAllByOrderByFechaNacimientoAsc();
}
