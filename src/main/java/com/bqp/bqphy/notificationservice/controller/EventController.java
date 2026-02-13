package com.bqp.bqphy.notificationservice.controller;

import com.bqp.bqphy.notificationservice.dto.EmailRequestDto;
import com.bqp.bqphy.notificationservice.service.EventPublisher;
import com.bqp.bqphy.notificationservice.service.MailService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification-manager")
public class EventController {

    private final EventPublisher publisher;
    private final MailService mailService;

    public EventController(EventPublisher publisher, MailService mailService, MailService mailService1) {
        this.publisher = publisher;
        this.mailService = mailService1;
    }

    @GetMapping("/test")
    public Map<String, Object> fireEvent() {
        publisher.publish();
        return Map.of("status", "SUCCESS", "message", "Event Fired", "timestamp", System.currentTimeMillis());
    }

    @PostMapping("/email")
    public Map<String, Object> triggerMail(@RequestBody EmailRequestDto request) {
        String subject = "Notification: " + request.getEvent();
        Map<String, Object> body = request.getInformation();

        mailService.sendEmail( request.getTo(), request.getCc(), subject, "body" );

        return Map.of(
            "status", "QUEUED",
            "event", request.getEvent(),
            "timestamp", System.currentTimeMillis()
        );
    }
}