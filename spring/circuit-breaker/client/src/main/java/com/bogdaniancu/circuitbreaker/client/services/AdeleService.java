package com.bogdaniancu.circuitbreaker.client.services;


import org.springframework.stereotype.Component;

@Component
public class AdeleService {

    private boolean doNotDisturb;

    public void setDoNotDisturb(boolean doNotDisturb) {
        this.doNotDisturb = doNotDisturb;
    }

    public void answer() {
        if (doNotDisturb) {
            throw new RuntimeException("The person you have called is unavailable right now, please try again later");
        }
        System.out.println("Hello... it's me");
    }

    public String answerBack() {
        if (doNotDisturb) {
            throw new RuntimeException("The person you have called is unavailable right now, please try again later");
        }
        return "Hello... it's me";
    }
}
