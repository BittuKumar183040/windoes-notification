package com.bqp.bqphy.notificationservice.service.builder;

import com.bqp.bqphy.notificationservice.service.TemplateInterface;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Component
public class RegistrationBuilder implements TemplateInterface {

    @Override
    public List<String> preTo() {
        return List.of("bittu.kumar@betoo.co.in", "cixadem596@advarm.com");
    }

    @Override
    public List<String> preCc() {
        return List.of("cixadem596@advarm.com");
    }

    @Override
    public String event() {
        return "USER_REGISTRATION";
    }

    @Override
    public String subject() {
        return "New User Registration";
    }

    @Override
    public String render(Map<String, Object> body) {
        String template = loadTemplate();

        if (body == null || body.isEmpty()) {
            return template;
        }

        String rendered = template;
        for (Map.Entry<String, Object> entry : body.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            String value = entry.getValue() != null ? entry.getValue().toString() : "";
            rendered = rendered.replace(placeholder, value);
        }
        rendered = rendered.replaceAll("\\$\\{[^}]+}", "");
        return rendered;
    }

    private String loadTemplate() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("templates/user-registration.html")) {
            if (is == null) {
                throw new RuntimeException("Email template not found: user-registration.html");
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load email template", e);
        }
    }
}
