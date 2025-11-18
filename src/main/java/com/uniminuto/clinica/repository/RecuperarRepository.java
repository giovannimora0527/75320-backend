package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecuperarRepository extends JpaRepository<RecuperarPasswordAuditoria, Long> {

}