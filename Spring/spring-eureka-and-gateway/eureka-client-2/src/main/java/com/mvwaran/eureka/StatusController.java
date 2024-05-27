package com.mvwaran.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/status")
    public String sayHello() {
        return "Hello from " + applicationName;
    }
}
