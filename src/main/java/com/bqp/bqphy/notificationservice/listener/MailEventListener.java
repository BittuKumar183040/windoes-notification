package com.bqp.bqphy.notificationservice.listener;

import com.bqp.bqphy.notificationservice.event.MailEvent;
import com.bqp.bqphy.notificationservice.service.EmailSenderInterface;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MailEventListener {

    private final EmailSenderInterface emailSender;

    public MailEventListener(EmailSenderInterface emailSender) {
        this.emailSender = emailSender;
    }

    @EventListener
    public void handleMailEvent(MailEvent event) {
        emailSender.submitEmail( event.getTo(), event.getCc(), event.getEvent(), event.getBody() );
    }
}
