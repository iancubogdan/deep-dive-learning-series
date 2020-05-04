package com.bogdaniancu.circuitbreaker.client.feign.controllers;

import com.bogdaniancu.circuitbreaker.client.feign.clients.builtinfallback.DemoClientWithBuiltInFallback;
import com.bogdaniancu.circuitbreaker.client.feign.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ClientController {

    private DemoClientWithBuiltInFallback demoClientWithBuiltInFallback;
    private ClientService clientService;

    public ClientController(DemoClientWithBuiltInFallback demoClientWithBuiltInFallback, ClientService clientService) {
        this.demoClientWithBuiltInFallback = demoClientWithBuiltInFallback;
        this.clientService = clientService;
    }

    @GetMapping("/getFromClient")
    public ResponseEntity<String> getFromClient() {
        System.out.println("Client: Hello");
        String response = demoClientWithBuiltInFallback.get();
        System.out.println("Service: " + response);
        return new ResponseEntity<>("Hello " + response, HttpStatus.OK);
    }

    @GetMapping("/getFromClient2")
    public ResponseEntity<String> getFromClient2() {
        System.out.println("Client: Hello");
        String response = clientService.get();
        System.out.println("Service: " + response);
        return new ResponseEntity<>("Hello " + response, HttpStatus.OK);
    }
}
