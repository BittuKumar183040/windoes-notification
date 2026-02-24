package com.bqp.bqphy.notificationservice.service.builder;

import com.bqp.bqphy.notificationservice.service.TemplateInterface;
import com.bqp.bqphy.notificationservice.service.impl.ThymeleafMailRenderer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegistrationBuilder implements TemplateInterface {

    private final ThymeleafMailRenderer thymeleafMailRenderer;

    public RegistrationBuilder(ThymeleafMailRenderer thymeleafMailRenderer) {
        this.thymeleafMailRenderer = thymeleafMailRenderer;
    }

    @Override
    public String event() {
        return "USER_REGISTRATION";
    }

    @Override
    public String subject() {
        return "BQPhy: New User Registration";
    }

    @Override
    public String render(Map<String, Object> body) {
        return thymeleafMailRenderer.render("user-registration", body);
    }

}
