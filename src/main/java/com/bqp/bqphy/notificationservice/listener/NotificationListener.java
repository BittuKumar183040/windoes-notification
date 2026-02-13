package com.bqp.bqphy.notificationservice.listener;

import com.bqp.bqphy.notificationservice.event.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @EventListener
    public Object handleEventAction(TestEvent event) {

        System.out.println("Got it");
        return null;
    }
}
