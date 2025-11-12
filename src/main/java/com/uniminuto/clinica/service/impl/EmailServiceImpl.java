package com.uniminuto.clinica.service.impl;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import com.uniminuto.clinica.model.EmailConfig;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.EmailService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    /**
     * Email Sender.
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Configuracion del SMTP.
     */
    @Autowired
    private EmailConfig emailConfig;

    @Override
    public void enviarCorreoSimple(final String to, final String subject,
                                   final String body)
            throws BadRequestException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(emailConfig.getTo());
            mailSender.send(message);
            System.out.println("Correo enviado exitosamente a: " + to);
        } catch (MailException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            throw new BadRequestException("Error. " + e.getMessage());
        }
    }

    @Override
    public String getTo() {
        return this.emailConfig.getTo();
    }
    
    @Override
    public String getFrom() {
        // El remitente debe ser el username (email) configurado, no el "to"
        return this.emailConfig.getUsername();
    }

    @Override
    public void enviarCorreo(final String to,
                             final String subject,
                             final String body,
                             final String from)
            throws BadRequestException, MessagingException {
        try {
            this.sendHtmlEmail(to, subject, body, from);
        } catch (MailException e) {
            System.err.println("Error al enviar el correo: "
                    + e.getMessage());
            throw new BadRequestException("Error. " + e.getMessage());
        } catch (MessagingException ex) {
            Logger.getLogger(EmailServiceImpl
                    .class.getName()).log(Level.SEVERE, null, ex);
            throw new MessagingException("Error => " + ex.getMessage());
        }
    }

    /**
     * Envia un correo HTML.
     * @param to para Destinatario.
     * @param subject Asunto.
     * @param htmlBody Cuerpo del correo.
     * @param from Remitente del correo.
     * @throws MessagingException excepcion.
     */
    @Override
    public void sendHtmlEmail(final String to,
                              final String subject,
                              final String htmlBody,
                              final String from)
            throws MessagingException {
        try {
            // Validar configuración
            if (emailConfig == null) {
                throw new MessagingException("EmailConfig no está configurado");
            }
            
            if (emailConfig.getSmtpHost() == null || emailConfig.getSmtpHost().isBlank()) {
                throw new MessagingException("SMTP Host no está configurado");
            }
            
            if (emailConfig.getUsername() == null || emailConfig.getUsername().isBlank()) {
                throw new MessagingException("SMTP Username no está configurado");
            }
            
            if (emailConfig.getPassword() == null || emailConfig.getPassword().isBlank()) {
                throw new MessagingException("SMTP Password no está configurado");
            }

            System.out.println("Configurando envío de correo:");
            System.out.println("  SMTP Host: " + emailConfig.getSmtpHost());
            System.out.println("  SMTP Port: " + emailConfig.getSmtpPort());
            System.out.println("  Username: " + emailConfig.getUsername());
            System.out.println("  From: " + from);
            System.out.println("  To: " + to);

            // Configuración de las propiedades del servidor SMTP
            Properties properties = new Properties();
            properties.put("mail.smtp.host", emailConfig.getSmtpHost());
            properties.put("mail.smtp.port", emailConfig.getSmtpPort());
            properties.put("mail.smtp.auth", emailConfig.getSmtpAuth());
            properties.put("mail.smtp.starttls.enable", emailConfig.getStartTls());
            properties.put("mail.smtp.ssl.enable", emailConfig.getSslEnable());
            // Agregar propiedades adicionales para mejor compatibilidad
            properties.put("mail.smtp.ssl.trust", emailConfig.getSmtpHost());
            properties.put("mail.smtp.connectiontimeout", "5000");
            properties.put("mail.smtp.timeout", "5000");

            // Crear la sesión de correo con autenticación
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConfig.getUsername(),
                            emailConfig.getPassword());
                }
            });

            // Crear el mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);

            // Establecer el cuerpo del mensaje en formato HTML
            message.setContent(htmlBody, "text/html; charset=utf-8");

            // Enviar el correo
            Transport.send(message);
            System.out.println("✓ Correo enviado con éxito a " + to);
        } catch (MessagingException e) {
            System.err.println("✗ Error al enviar correo a " + to);
            System.err.println("  Mensaje: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("  Causa: " + e.getCause().getMessage());
            }
            throw e;
        } catch (Exception e) {
            System.err.println("✗ Error inesperado al enviar correo a " + to);
            System.err.println("  Tipo: " + e.getClass().getSimpleName());
            System.err.println("  Mensaje: " + e.getMessage());
            throw new MessagingException("Error al enviar correo: " + e.getMessage(), e);
        }
    }

    @Override
    public RespuestaRs testEmail(String correoDestinatario) throws BadRequestException, MessagingException {
        this.sendHtmlEmail(correoDestinatario, "Prueba de correo",
                "<h1>Este es un correo de prueba</h1>"
                + "<p>Enviado desde el servicio de email.</p>",
                this.getTo());
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMensaje("Correo enviado exitosamente a " + correoDestinatario);
        return rta;
    }
}
