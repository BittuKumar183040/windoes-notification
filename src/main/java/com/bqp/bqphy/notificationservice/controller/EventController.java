package com.bqp.bqphy.notificationservice.controller;

import com.bqp.bqphy.notificationservice.dto.EmailRequestDto;
import com.bqp.bqphy.notificationservice.*;
import com.bqp.bqphy.notificationservice.service.EmailSenderInterface;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification-manager")
public class EventController {

    private final EmailSenderInterface emailSender;

    public EventController(EmailSenderInterface emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/registration")
    public Map<String, Object> triggerMail(@RequestBody EmailRequestDto request) {

        Map<String, Object> body = request.getInformation();
        String event = request.getEvent();
        emailSender.submitEmail( request.getTo(), request.getCc(), event, body);

        return Map.of(
            "status", "QUEUED",
            "event", request.getEvent(),
            "timestamp", System.currentTimeMillis()
        );
    }
}