package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.AuditoriaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaLogRepository extends
        JpaRepository<AuditoriaLog, Long>,
        JpaSpecificationExecutor<AuditoriaLog> {
}
