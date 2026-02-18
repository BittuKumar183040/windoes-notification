package com.bqp.bqphy.notificationservice.event;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class MailEvent {
    private final List<String> to;
    private final List<String> cc;
    private final String event;
    private final Map<String, Object> body;

    public MailEvent(List<String> to, List<String> cc, String event, Map<String, Object> body) {
        this.to = to;
        this.cc = cc;
        this.event = event;
        this.body = body;
    }
}