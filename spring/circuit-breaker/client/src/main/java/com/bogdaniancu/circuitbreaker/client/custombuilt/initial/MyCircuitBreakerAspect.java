//package com.bogdaniancu.circuitbreaker.client.custombuilt;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
////@Aspect
////@Component
//public class MyCircuitBreakerAspect {
//
//    @Autowired
//    private CircuitBreaker circuitBreaker;
//
//
//    @Around("@annotation(com.bogdaniancu.circuitbreaker.client.custombuilt.initial.CircuitBreakerFirstTry)")
//    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
////        System.out.println("Do something before");
//        Object returnedObject = null;
//        System.out.println("Current Fail count: " + circuitBreaker.getFailureCount());
//        System.out.println("Circuit Breaker state: " + circuitBreaker.getState());
//        try {
//            if (circuitBreaker.getState().equals(CircuitBreaker.State.Closed) ||
//                    circuitBreaker.getState().equals(CircuitBreaker.State.HalfOpen)) {
//                returnedObject = joinPoint.proceed();
//                circuitBreaker.reset();
//            } else {
//                System.out.println("Circuit breaker is " + circuitBreaker.getState() + ", call not forwarded");
//            }
//        } catch (Exception e) {
//            System.out.println("Exception caught");
//            circuitBreaker.recordFailure();
//        }
//
////        System.out.println("Do something after");
//        return returnedObject;
//    }
//}
