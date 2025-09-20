
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andre
 */
@Repository
public interface RecetaRepository extends JpaRepository <Receta,Long>{
}
