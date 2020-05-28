package com.bogdaniancu.circuitbreaker.client.custombuilt.initial.service;

//import com.bogdaniancu.circuitbreaker.client.custombuilt.initial.CircuitBreakerFirstTry;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ServiceA {
//
//    private ServiceB serviceB;
//
//    public ServiceA(ServiceB serviceB) {
//        this.serviceB = serviceB;
//    }
//
//    public void setFail(boolean fail) {
//        serviceB.setFail(fail);
//    }
//
//    @CircuitBreakerFirstTry
//    public void call() {
//        serviceB.call();
//    }
//
//    @CircuitBreakerFirstTry
//    public void callFail() {
//        serviceB.callFail();
//    }
//
//    public static void main(String[] args) {
//
//    }
//}