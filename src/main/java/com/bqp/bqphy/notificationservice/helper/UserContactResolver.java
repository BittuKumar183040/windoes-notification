package com.bqp.bqphy.notificationservice.helper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserContactResolver {
    private static final String SIMPLE_EMAIL_REGEX = ".+@.+\\..+";

    public List<String> resolveAll(List<String> values) {
        if (values == null || values.isEmpty()) {
            return List.of();
        }

        return values.stream()
            .map(this::resolve)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private String retrieveEmail(String id) {
        return null;
    }

    private String resolve(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        if (value.matches(SIMPLE_EMAIL_REGEX)) {
            return value;
        }

        return retrieveEmail(value);
    }
}
