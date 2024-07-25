package com.vaibhav.journalapp.Controller;

import org.springframework.web.bind.annotation.RestController;

@RestController("/health-check")
public class HealthCheck {
    public  String healthCheck(){
        return "ok";
    }
}
