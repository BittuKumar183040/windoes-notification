package com.bqp.bqphy.notificationservice.listener;

import com.bqp.bqphy.notificationservice.event.MailEvent;
import com.bqp.bqphy.notificationservice.service.MailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MailEventListener {

    private final MailService mailService;

    public MailEventListener(MailService mailService) {
        this.mailService = mailService;
    }

    @EventListener
    public void handleMailEvent(MailEvent event) {
        mailService.sendEmail(
                event.getTo(),
                event.getCc(),
                event.getSubject(),
                event.getBody()
        );
    }
}
