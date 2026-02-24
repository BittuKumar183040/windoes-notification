package com.bqp.bqphy.notificationservice.controller;

import com.bqp.bqphy.notificationservice.service.impl.ThymeleafMailRenderer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/preview")
public class EmailPreviewController {

    private final ThymeleafMailRenderer thymeleafMailRenderer;

    public EmailPreviewController(ThymeleafMailRenderer thymeleafMailRenderer) {
        this.thymeleafMailRenderer = thymeleafMailRenderer;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String previewUserRegistration() {
        Map<String, Object> body = new HashMap<>();
        body.put("username", "John Doe");
        body.put("id", "USR-1234");
        body.put("email", "abc@gmail.com");
        body.put("createdAt", "2026-02-22");

        return thymeleafMailRenderer.render("user-registration", body);
    }
}
