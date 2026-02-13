package com.bqp.bqphy.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(List<String> to, List<String> cc, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        if (to != null && !to.isEmpty()) {
            message.setTo(to.toArray(new String[0]));
        }
        if (cc != null && !cc.isEmpty()) {
            message.setCc(cc.toArray(new String[0]));
        }
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
