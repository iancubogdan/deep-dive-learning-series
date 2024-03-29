package com.bogdaniancu.prototypeservice.events;

import com.bogdaniancu.prototypeservice.helpers.ThreadData;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        System.out.println("Current thread " + Thread.currentThread().getId());
        System.out.println("ThreadData: " + ThreadData.getUuid());
        System.out.println("Received spring custom event - " + event.getMessage());
    }
}
