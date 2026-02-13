package com.bqp.bqphy.notificationservice.service;

import com.bqp.bqphy.notificationservice.event.TestEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish() {
        publisher.publishEvent(new TestEvent());
    }
}
