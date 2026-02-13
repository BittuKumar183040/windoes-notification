package com.bqp.bqphy.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class EmailRequestDto {

    private List<String> to;
    private List<String> cc;
    private String event;
    private Map<String, Object> information;

}
