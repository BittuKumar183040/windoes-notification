package com.bqp.bqphy.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NotificationmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationmanagerApplication.class, args);
	}

}
