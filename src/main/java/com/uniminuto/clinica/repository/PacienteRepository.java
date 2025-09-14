    package com.uniminuto.clinica.repository;

    import com.uniminuto.clinica.entity.Paciente;
    import java.util.Optional;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import java.util.List;

    /**
     *Repositorio de datos para la tabla Paciente
     * @author nicolas
     */
    @Repository
    public interface PacienteRepository extends JpaRepository<Paciente, Long> {
        Optional<Paciente> findByNumeroDocumento(String numero_documento);
        List<Paciente> findAllByOrderByFechaNacimientoAsc();
    }
