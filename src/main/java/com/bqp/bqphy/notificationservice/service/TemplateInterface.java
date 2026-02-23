package com.bqp.bqphy.notificationservice.service;

import java.util.List;
import java.util.Map;

public interface TemplateInterface {
    default List<String> preTo() { return List.of(); }
    default List<String> preCc() { return List.of(); }

    String event();
    String subject();
    String render(Map<String, Object> body);
}