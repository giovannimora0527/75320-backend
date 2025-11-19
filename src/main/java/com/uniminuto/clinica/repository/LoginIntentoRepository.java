
package com.uniminuto.clinica.repository;
import com.uniminuto.clinica.entity.LoginIntento;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andre
 */
public interface LoginIntentoRepository extends JpaRepository<LoginIntento, Long> {

    List<LoginIntento> findByUsuarioId(Long usuarioId);

}
