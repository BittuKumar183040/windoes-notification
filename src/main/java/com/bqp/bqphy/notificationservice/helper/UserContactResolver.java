package com.bqp.bqphy.notificationservice.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserContactResolver {

    private static final String SIMPLE_EMAIL_REGEX = ".+@.+\\..+";

    @Value("${service.IDENTITY_SERVICE}")
    private String identityServiceUrl;

    private final WebClient webClient = WebClient.create();

    public List<String> resolveAll(List<String> values) {

        if (values == null || values.isEmpty()) {
            return List.of();
        }

        List<String> emails = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        for (String value : values) {
            if (value == null || value.isBlank()) continue;

            if (value.matches(SIMPLE_EMAIL_REGEX)) {
                emails.add(value);
            } else {
                ids.add(value);
            }
        }

        if (!ids.isEmpty()) {
            emails.addAll(fetchEmails(ids));
        }

        return emails;
    }

    private List<String> fetchEmails(List<String> ids) {

        List<Map> response = webClient.post()
            .uri(identityServiceUrl + "/identity-management/users/info")
            .bodyValue(ids)
            .retrieve()
            .bodyToFlux(Map.class)
            .collectList()
            .block();

        if (response == null) return List.of();

        return response.stream()
                .map(user -> (String) user.get("email"))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}