package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.RecuperacionContrasenaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RecuperacionContrasenaLogRepository
        extends JpaRepository<RecuperacionContrasenaLog, Long> {
}