package com.bogdaniancu.circuitbreaker.client.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public ResponseEntity<String> demoEndpoint() {
//        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
        return new ResponseEntity("It's me", HttpStatus.OK);
    }

}
