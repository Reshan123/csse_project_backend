package com.HospitalManagementSystem.HospitalManagementSystem.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.ByteArrayOutputStream;

public class EmailUtil {

    private static EmailUtil instance;

    private final JavaMailSender mailSender;

    // Private constructor to prevent instantiation
    private EmailUtil(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Public method to get the singleton instance
    public static synchronized EmailUtil getInstance(JavaMailSender mailSender) {
        if (instance == null) {
            instance = new EmailUtil(mailSender);
        }
        return instance;
    }

    public void sendSignUpEmail(String toEmail, String rawPassword, ByteArrayOutputStream qrCodeOutputStream) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("mithilathilochana@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("Welcome to the Hospital Management System");
        helper.setText("Dear user,\n\nWelcome! Your account has been created successfully. " +
                "Please find your login credentials and a QR code below:\n\n" +
                "Password: " + rawPassword + "\n\n" +
                "Scan this QR code to view your details.\n\n" +
                "Thank you!");

        // Attach QR Code
        helper.addAttachment("QRCode.png", new ByteArrayResource(qrCodeOutputStream.toByteArray()));

        mailSender.send(message);
    }
}
