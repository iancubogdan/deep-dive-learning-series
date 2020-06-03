package com.bogdaniancu.circuitbreaker.client.feign.clients;

public class BusinessError extends RuntimeException {

    public BusinessError(String message) {
        super(message);
    }
}
