package com.bogdaniancu.circuitbreaker.client.feign.clients.builtinfallback;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "demoClientFallback", url = "localhost:8090", configuration = DemoFeignClientConfiguration.class)
public interface DemoClientWithBuiltInFallback {
    @GetMapping("/demo")
    @CircuitBreaker(name = "demoClient")
    String get();
}