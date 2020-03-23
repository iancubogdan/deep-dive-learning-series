package com.bogdaniancu.prototypeservice.events;

import com.bogdaniancu.prototypeservice.helpers.ThreadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishEvent(final String message) {
        System.out.println("Current thread " + Thread.currentThread().getId());
        System.out.println("ThreadData: " + ThreadData.getUuid());
        System.out.println("Publishing custom event. ");
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
