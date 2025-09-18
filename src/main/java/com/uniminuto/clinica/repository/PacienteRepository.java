package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByNumeroDocumento(String documento);

    /**
     *
     * @author JulianLopez
     *
     * Pacientes ordenados por fecha de nacimiento ASC (más viejo -> más joven)
     * @return 
     *
     */
    List<Paciente> findAllByOrderByFechaNacimientoAsc();

}
