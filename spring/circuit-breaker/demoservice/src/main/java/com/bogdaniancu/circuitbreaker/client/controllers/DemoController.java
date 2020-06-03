package com.bogdaniancu.circuitbreaker.client.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    private boolean timeout;
    private boolean fail;
    private boolean businessError;

    @GetMapping("/demo")
    public ResponseEntity<String> demoEndpoint() throws InterruptedException {
        if (fail) {
            throw new RuntimeException("Something bad happened");
        }
        if (businessError) {
            return new ResponseEntity<>("Business error", HttpStatus. UNPROCESSABLE_ENTITY);
        }
        if (timeout) {
            Thread.sleep(20000);
        }
        return new ResponseEntity("It's me again", HttpStatus.OK);
    }

    @PostMapping("/setTimeout")
    public ResponseEntity<String> setTimeout() {
        timeout = true;
        return new ResponseEntity<>("set Timeout", HttpStatus.OK);
    }

    @PostMapping("/setFail")
    public ResponseEntity<String> setFail() {
        fail = true;
        return new ResponseEntity<>("set Fail", HttpStatus.OK);
    }

    @PostMapping("/setBusinessError")
    public ResponseEntity<String> setBusinessError() {
        businessError = true;
        return new ResponseEntity<>("set BusinessError", HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> reset() {
        fail = false;
        timeout = false;
        return new ResponseEntity<>("reset", HttpStatus.OK);
    }

}
