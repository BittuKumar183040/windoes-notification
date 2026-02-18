package com.bqp.bqphy.notificationservice.helper;

import com.bqp.bqphy.notificationservice.service.TemplateInterface;
import com.bqp.bqphy.notificationservice.service.builder.RegistrationBuilder;
import org.springframework.stereotype.Component;


@Component
public class EmailTemplateResolver {

    public TemplateInterface resolve(String email, String event) {

        if (event == null) {
            throw new IllegalArgumentException("Missing 'event' field in mail body");
        }

        return switch (event) {
            case "USER_REGISTRATION" -> new RegistrationBuilder();
            case "ASSIGNED_TENANT" -> new RegistrationBuilder();
            default -> throw new IllegalArgumentException("Unsupported event: " + event);
        };
    }
}

