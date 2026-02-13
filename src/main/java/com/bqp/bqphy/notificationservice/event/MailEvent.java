package com.bqp.bqphy.notificationservice.event;

import lombok.Getter;

import java.util.List;

@Getter
public class MailEvent {
    private final List<String> to;
    private final List<String> cc;
    private final String subject;
    private final String body;

    public MailEvent(List<String> to, List<String> cc, String subject, String body) {
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.body = body;
    }
}