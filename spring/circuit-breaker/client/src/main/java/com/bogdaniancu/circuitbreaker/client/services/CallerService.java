package com.bogdaniancu.circuitbreaker.client.services;

import com.bogdaniancu.circuitbreaker.client.custombuilt.MyCircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallerService {

    @Autowired
    private AdeleService adeleService;

    @MyCircuitBreaker
    public void call() {
        adeleService.answer();
    }
}
