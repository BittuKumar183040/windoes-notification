package com.bqp.bqphy.notificationservice.service.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class ThymeleafMailRenderer {

    private final TemplateEngine templateEngine;

    public ThymeleafMailRenderer(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String render(String templateName, Map<String, Object> body) {

        Context context = new Context();

        if (body != null) {
            body.forEach(context::setVariable);
        }

        return templateEngine.process(templateName, context);
    }
}