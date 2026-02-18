package com.bqp.bqphy.notificationservice.service;

import java.util.List;
import java.util.Map;

public interface EmailSenderInterface {
    public void submitEmail( List<String> to, List<String> cc, String event, Map<String, Object> body);
}
