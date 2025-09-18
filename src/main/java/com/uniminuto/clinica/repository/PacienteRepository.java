package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
<<<<<<< HEAD
import java.util.List;
=======
import com.uniminuto.clinica.entity.Usuario;
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByNumeroDocumento(String documento);

    List<Paciente> findAllByOrderByFechaNacimientoAsc();
=======
/**
 *
 * @author Julian
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByNumeroDocumento(String numerodocumento);

>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
}
