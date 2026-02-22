package com.bqp.bqphy.notificationservice.helper;

import com.bqp.bqphy.notificationservice.service.TemplateInterface;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EmailTemplateResolver {

    private final Map<String, TemplateInterface> templates;

    public EmailTemplateResolver(java.util.List<TemplateInterface> templateList) {
        this.templates = templateList.stream()
            .collect(Collectors.toMap( TemplateInterface::event, Function.identity() ));
    }

    public TemplateInterface resolve(String event) {

        TemplateInterface template = templates.get(event);

        if (template == null) {
            throw new IllegalArgumentException("Unsupported event: " + event);
        }

        return template;
    }

}
