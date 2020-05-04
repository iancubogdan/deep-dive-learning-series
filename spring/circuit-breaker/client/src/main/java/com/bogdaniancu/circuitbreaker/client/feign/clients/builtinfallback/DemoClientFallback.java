package com.bogdaniancu.circuitbreaker.client.feign.clients.builtinfallback;

import org.springframework.stereotype.Component;

@Component
public class DemoClientFallback implements DemoClientWithBuiltInFallback {

    @Override
    public String get() {
        return "It's not the real me";
    }
}
