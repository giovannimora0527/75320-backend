package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RecuperarRepository extends JpaRepository<RecuperarPasswordAuditoria, Long> {

    // NUEVA CONSULTA
    @Query("SELECT COUNT(a) FROM RecuperarPasswordAuditoria a WHERE a.username = :username AND a.tipoAuditoria = 'LOGIN' AND a.description LIKE 'Intento fallido%' AND a.transaccionFecha > :fechaLimite")
    long countIntentosFallidosLoginRecientes(@Param("username") String username, @Param("fechaLimite") LocalDateTime fechaLimite);
}