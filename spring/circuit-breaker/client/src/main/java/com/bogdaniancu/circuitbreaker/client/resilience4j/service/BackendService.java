package com.bogdaniancu.circuitbreaker.client.resilience4j.service;

@FunctionalInterface
public interface BackendService {

    String returnUppercase(String string);
}
