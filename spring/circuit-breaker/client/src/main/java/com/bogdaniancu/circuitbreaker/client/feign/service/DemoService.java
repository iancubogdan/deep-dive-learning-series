package com.bogdaniancu.circuitbreaker.client.feign.service;

import com.bogdaniancu.circuitbreaker.client.feign.clients.DemoClient;
import com.bogdaniancu.circuitbreaker.client.feign.clients.ServiceError;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class DemoService {

    private DemoClient demoClient;

    public DemoService(DemoClient demoClient) {
        this.demoClient = demoClient;
    }

    @CircuitBreaker(name = "demoService", fallbackMethod = "getFallback")
    public String get() {
        return demoClient.get();
    }

    public String failure() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    public String getFallback(ServiceError serviceError) {
        return "It's really not the real me";
    }
}
