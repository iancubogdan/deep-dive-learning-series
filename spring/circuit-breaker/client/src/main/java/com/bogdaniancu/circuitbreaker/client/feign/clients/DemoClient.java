package com.bogdaniancu.circuitbreaker.client.feign.clients;

import com.bogdaniancu.circuitbreaker.client.feign.clients.builtinfallback.DemoFeignClientConfiguration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "demoClient", url = "http://demoservice.localhost")
public interface DemoClient {
    @GetMapping("/demo")
    String get();
}