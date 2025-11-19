package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.AuditoriaRecuperacionPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Andre
 */
public interface AuditoriaRecuperacionPasswordRepository 
    extends JpaRepository<AuditoriaRecuperacionPassword, Long>{
    
}
