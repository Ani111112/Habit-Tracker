package com.schedulerservice.scheduler_service.consumer;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomMailSender {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.from}")
    private String from;

    public void sendEmail(String template, String toMail, String fromMail, String subject) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(toMail);
//        message.setSubject(subject);
////        message.setText(template);
//        message.setText(template, "text/html; charset=utf-8");
//
//        javaMailSender.send(message);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(template, true); // ✅ true = HTML

            javaMailSender.send(message);
            log.info("Email Sent Successfully");
        } catch (Exception ex) {
            log.error("Exception Occurred: {}", ex.getMessage());
        }
//        System.out.println("✅ Email sent successfully to " + to);
    }
}
