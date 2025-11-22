package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.AuditoriaLog;
import com.uniminuto.clinica.repository.AuditoriaLogRepository;
import com.uniminuto.clinica.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaLogRepository auditoriaRepo;

    @Override
    public void registrarIntentoFallidoLogin(String username, String descripcion, String ip) {
        guardarLog(username, "LOGIN_FALLIDO", descripcion, ip, false);
    }

    @Override
    public void registrarRecuperacionFallida(String username, String descripcion, String ip) {
        guardarLog(username, "RECUPERACION_FALLIDA", descripcion, ip, false);
    }

    @Override
    public void registrarBloqueo(String username, String descripcion, String ip) {
        guardarLog(username, "CUENTA_BLOQUEADA", descripcion, ip, false);
    }

    @Override
    public void registrarRecuperacionExitosa(String username, String ip) {
        guardarLog(username, "RECUPERACION_EXITOSA", "Recuperación exitosa", ip, true);
    }

    private void guardarLog(String username, String tipo, String descripcion, String ip, boolean exitoso) {
        AuditoriaLog log = new AuditoriaLog();
        log.setUsername(username);
        log.setTipoEvento(tipo);
        log.setDescripcion(descripcion);
        log.setDireccionIp(ip);
        log.setFechaEvento(LocalDateTime.now());
        log.setExitoso(exitoso);
        auditoriaRepo.save(log);
    }

    @Override
    public Page<AuditoriaLog> buscarAuditoria(
            String usuario, String tipo, LocalDateTime fechaDesde,
            LocalDateTime fechaHasta, int page, int size) {

        Specification<AuditoriaLog> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (usuario != null && !usuario.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("username")), "%" + usuario.toLowerCase() + "%"));
            }

            if (tipo != null && !tipo.equals("Todos")) {
                predicates.add(cb.equal(root.get("tipoEvento"), tipo));
            }

            if (fechaDesde != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("fechaEvento"), fechaDesde));
            }

            if (fechaHasta != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("fechaEvento"), fechaHasta));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fechaEvento"));

        return auditoriaRepo.findAll(spec, pageable);
    }
}
