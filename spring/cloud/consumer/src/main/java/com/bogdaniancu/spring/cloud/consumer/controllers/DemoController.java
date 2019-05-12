package com.bogdaniancu.spring.cloud.consumer.controllers;

import com.bogdaniancu.spring.cloud.consumer.clients.ProducerClient;
import com.bogdaniancu.spring.cloud.consumer.services.HystrixDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private ProducerClient producerClient;
    @Autowired
    private HystrixDemoService hystrixDemoService;

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        String output = "I have retrieved: " + producerClient.getDummyData();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/demoHystrix")
    public ResponseEntity<String> demoHystrix() {
        String output = "I have retrieved: " + hystrixDemoService.getDummyData();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
