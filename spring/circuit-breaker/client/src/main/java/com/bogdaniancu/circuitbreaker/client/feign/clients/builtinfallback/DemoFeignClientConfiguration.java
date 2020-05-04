package com.bogdaniancu.circuitbreaker.client.feign.clients.builtinfallback;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@RequiredArgsConstructor
public class DemoFeignClientConfiguration {
//    private final CircuitBreakerRegistry registry;
    private final DemoClientFallback demoClientFallback;

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(20)
                .slowCallRateThreshold(20)
                .slowCallDurationThreshold(Duration.ofMinutes(1))
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        return registry;
    }

    @Bean
    public Resilience4jFeign.Builder feignBuilder(CircuitBreakerRegistry registry) {

        CircuitBreaker circuitBreaker = registry.circuitBreaker("demoClient");
        FeignDecorators decorators = FeignDecorators.builder()
                .withCircuitBreaker(circuitBreaker)
                .withFallback(demoClientFallback)
                .build();
        return Resilience4jFeign.builder(decorators);
    }
}
