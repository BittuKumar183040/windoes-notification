package com.bqp.bqphy.notificationservice.service;

import java.util.List;
import java.util.Map;

public interface TemplateInterface {
    List<String> preTo();
    List<String> preCc();
    String event();
    String subject();
    String render(Map<String, Object> body);
}