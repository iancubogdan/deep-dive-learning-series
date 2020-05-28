package com.bogdaniancu.circuitbreaker.client.feign.controllers;

import com.bogdaniancu.circuitbreaker.client.feign.service.DemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallerController {

    private DemoService demoService;

    public CallerController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/call")
    public ResponseEntity<String> getFromClient() {
        System.out.println("Client: Hello");
        String response = demoService.get();
        System.out.println("Service: " + response);
        return new ResponseEntity<>("Hello " + response, HttpStatus.OK);
    }
}
