package com.bqp.bqphy.notificationservice.controller;

import com.bqp.bqphy.notificationservice.dto.EmailRequestDto;
import com.bqp.bqphy.notificationservice.event.MailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification-service")
@Slf4j
public class EventController {

    private final ApplicationEventPublisher eventPublisher;

    public EventController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/")
    public Map<String, Object> triggerMail(@RequestBody EmailRequestDto request) {
        log.info("Requested for {} with body {}", request.getEvent(), request.getInformation().toString());
        MailEvent event = new MailEvent( request.getTo(), request.getCc(), request.getEvent(), request.getInformation() );
        log.trace("Request Processing for {}:{}:{}:{}",  request.getTo(), request.getCc(), request.getEvent(), request.getInformation());
        eventPublisher.publishEvent(event);
        return Map.of( "status", "QUEUED", "event", request.getEvent(), "timestamp", System.currentTimeMillis() );
    }

}