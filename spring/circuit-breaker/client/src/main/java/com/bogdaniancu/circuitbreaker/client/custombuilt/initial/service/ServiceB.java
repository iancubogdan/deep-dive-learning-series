package com.bogdaniancu.circuitbreaker.client.custombuilt.initial.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceB {

    private boolean fail;

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public void call() {
        if (fail) {
            throw new RuntimeException("Oops");
        }
        System.out.println("Hello, this is service B");
    }

    public void callFail() {
        System.out.println("Hello, this is service B");

    }
}
