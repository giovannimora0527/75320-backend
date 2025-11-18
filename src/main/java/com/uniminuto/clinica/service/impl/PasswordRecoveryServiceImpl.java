package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.PasswordRecoveryRequest;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.repository.RecuperacionContrasenaLogRepository;
import com.uniminuto.clinica.entity.RecuperacionContrasenaLog;
import com.uniminuto.clinica.service.PasswordRecoveryService;
import com.uniminuto.clinica.service.CifrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecuperacionContrasenaLogRepository logRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CifrarService cifrarService;

    private String generarPasswordTemporal() {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789@#$%";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(chars.charAt(r.nextInt(chars.length())));
        return sb.toString();
    }

    private void log(String user, String desc, String ip) {
        RecuperacionContrasenaLog log = new RecuperacionContrasenaLog();
        log.setFechaHora(LocalDateTime.now());
        log.setUsername(user);
        log.setDescripcion(desc);
        log.setIpOrigen(ip);
        logRepository.save(log);
    }

    @Override
    @Transactional
    public void recuperarContrasena(PasswordRecoveryRequest request, String ipOrigen) {

        String username = request.getUsername();
        Optional<Usuario> opt = usuarioRepository.findByUsername(username);

        if (opt.isEmpty()) {
            log(username, "Usuario inválido en recuperación de contraseña", ipOrigen);
            return;
        }

        Usuario user = opt.get();

        String tempPass = generarPasswordTemporal();
        String hashed = cifrarService.encriptarPassword(tempPass);

        // Ajustado: usa el setter de contraseña más común. Cambia a tu setter real si es distinto.
        user.setPassword(hashed);
        usuarioRepository.save(user);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Recuperación de contraseña");
        email.setText(
                "Hola " + user.getUsername() + "\n\n" +
                        "Tu nueva contraseña temporal es: " + tempPass + "\n" +
                        "Cámbiala después de iniciar sesión."
        );
        mailSender.send(email);

        log(username, "Contraseña temporal enviada al correo", ipOrigen);
    }
}
