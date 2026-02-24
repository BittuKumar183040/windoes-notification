package com.bqp.bqphy.notificationservice.service.impl;

import com.bqp.bqphy.notificationservice.config.UserRegConfiguration;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MailService implements EmailSenderInterface {

    private final JavaMailSender mailSender;
    private final EmailTemplateResolver templateResolver;
    private final UserContactResolver userContactResolver;
    private final UserRegConfiguration userRegConfigurationProperties;

    public MailService(JavaMailSender mailSender, EmailTemplateResolver templateResolver, UserContactResolver userContactResolver, UserRegConfiguration userRegConfigurationProperties) {
        this.mailSender = mailSender;
        this.templateResolver = templateResolver;
        this.userContactResolver = userContactResolver;
        this.userRegConfigurationProperties = userRegConfigurationProperties;
    }

    public List<String> preTo() {
        return userRegConfigurationProperties.getTo();
    }

    public List<String> preCc() {
        return userRegConfigurationProperties.getCc();
    }

    @Override
    @Async
    public void submitEmail(List<String> to, List<String> cc, String event, Map<String, Object> body) {
        List<String> resolvedTo = userContactResolver.resolveAll(to);
        List<String> resolvedCc = userContactResolver.resolveAll(cc);

        log.trace("Submitting email with body {} for event = {}, on Emails to: {}, cc: {}", body.toString(), event, resolvedTo.toString(), resolvedCc.toString());
        TemplateInterface template = templateResolver.resolve(event);

        List<String> toEmails = new ArrayList<>();
        toEmails.addAll(preTo()); toEmails.addAll(resolvedTo);

        List<String> ccEmails = new ArrayList<>();
        ccEmails.addAll(preCc()); ccEmails.addAll(resolvedCc);

        toEmails = toEmails.stream().distinct().toList();
        ccEmails = ccEmails.stream().distinct().toList();

        sendEmail(toEmails, ccEmails, template.subject(), template.render(body));

        log.info("Email sent to {}", toEmails);
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
