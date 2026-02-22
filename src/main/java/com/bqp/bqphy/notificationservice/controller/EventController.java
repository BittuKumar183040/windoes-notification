package com.bqp.bqphy.notificationservice.controller;

import com.bqp.bqphy.notificationservice.dto.EmailRequestDto;
import com.bqp.bqphy.notificationservice.event.MailEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification-service")
public class EventController {

    private final ApplicationEventPublisher eventPublisher;

    public EventController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/registration")
    public Map<String, Object> triggerMail(@RequestBody EmailRequestDto request) {
        MailEvent event = new MailEvent( request.getTo(), request.getCc(), request.getEvent(), request.getInformation() );
        eventPublisher.publishEvent(event);
        return Map.of( "status", "QUEUED", "event", request.getEvent(), "timestamp", System.currentTimeMillis() );
    }

}