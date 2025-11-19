package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.LoginIntentoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginIntentoLogRepository extends JpaRepository<LoginIntentoLog, Long> {
}