package com.bogdaniancu.circuitbreaker.client.feign.clients;

public class ServiceError extends RuntimeException {

    public ServiceError(String message) {
        super(message);
    }
}
