package org.example.polling_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Polling API is running";
    }

    @GetMapping("/api/test")
    public String test() {
        return "API test endpoint working";
    }
}