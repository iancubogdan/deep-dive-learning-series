package com.bogdaniancu.circuitbreaker.client.resilience4j;

import com.bogdaniancu.circuitbreaker.client.resilience4j.service.BackendService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;

import java.util.function.Function;
import java.util.function.Supplier;

public class CircuitBreakerDemo {

    public void circuitBreakCall(CircuitBreaker circuitBreaker, BackendService service, String parameter) {
        Supplier<String> decorated = CircuitBreaker.decorateSupplier(circuitBreaker,  () -> service.returnUppercase(parameter));
    }

    public static void main(String[] args) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(5)
                .minimumNumberOfCalls(4)
                .recordExceptions(Throwable.class)
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        BackendService service = string -> {
            System.out.println("here");
            if (string.contains("5")) {
                throw new RuntimeException();
            }
            return string.toUpperCase();
        };


        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("cb");
        Function<String, String> decoratedFunction = CircuitBreaker.decorateFunction(circuitBreaker, service::returnUppercase);
        for (int i = 0; i < 10; i++) {
            System.out.println("Attempt #" + (i+1));
            int finalI = i;
            Supplier<String> decorated = CircuitBreaker.decorateSupplier(circuitBreaker,  () -> service.returnUppercase("try"+ finalI));
            String result = Try.ofSupplier(decorated).recover(throwable -> "hello From recovery").get();
//            String result2 = decoratedFunction.apply("try"+i);
//            if (i==4) {
//                circuitBreaker.transitionToForcedOpenState();
//            }
            System.out.println(result);
//            System.out.println(result2);
        }
//        System.out.println(result);


    }
}
