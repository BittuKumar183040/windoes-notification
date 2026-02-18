package com.bqp.bqphy.notificationservice.service.impl;

import com.bqp.bqphy.notificationservice.helper.EmailTemplateResolver;
import com.bqp.bqphy.notificationservice.helper.UserContactResolver;
import com.bqp.bqphy.notificationservice.service.EmailSenderInterface;
import com.bqp.bqphy.notificationservice.service.TemplateInterface;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MailService implements EmailSenderInterface {

    private final JavaMailSender mailSender;
    private final EmailTemplateResolver templateResolver;
    private final UserContactResolver userContactResolver;

    public MailService(JavaMailSender mailSender, EmailTemplateResolver templateResolver, UserContactResolver userContactResolver) {
        this.mailSender = mailSender;
        this.templateResolver = templateResolver;
        this.userContactResolver = userContactResolver;
    }

    @Override
    @Async
    public void submitEmail(List<String> to, List<String> cc, String event, Map<String, Object> body) {
        log.info("Submitting email for event = {}", event);
        List<String> toEmails = userContactResolver.resolveAll(to);
        List<String> ccEmails = userContactResolver.resolveAll(cc);

        if (toEmails.isEmpty()) { log.warn("No valid recipients found. Skipping send."); return; }

        for (String recipient : toEmails) {
            try {
                TemplateInterface template = templateResolver.resolve(recipient, event);
                sendEmail(List.of(recipient), ccEmails, template.subject(), template.render());
                log.info("Email sent to {}", recipient);
            }
            catch (Exception ex) {
                log.error("Failed to send email to {}", recipient, ex);
            }
        }
    }

    private void sendEmail( List<String> to, List<String> cc, String subject, String body ) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to.toArray(String[]::new));

            if (cc != null && !cc.isEmpty()) {
                helper.setCc(cc.toArray(String[]::new));
            }

            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to send email", ex);
        }
    }
}
