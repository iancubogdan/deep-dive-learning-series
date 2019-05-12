package com.bogdaniancu.spring.cloud.producer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummyData")
public class DummyController {

    @GetMapping
    public ResponseEntity<String> dummyData() {
        return new ResponseEntity<>("Just some dummy data", HttpStatus.OK);
    }
}
