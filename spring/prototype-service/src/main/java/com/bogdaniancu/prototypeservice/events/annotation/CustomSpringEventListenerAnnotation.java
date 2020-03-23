package com.bogdaniancu.prototypeservice.events.annotation;

import com.bogdaniancu.prototypeservice.events.CustomSpringEvent;
import com.bogdaniancu.prototypeservice.helpers.ThreadData;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class CustomSpringEventListenerAnnotation {

    @EventListener
    @Async
    public void onApplicationEvent(CustomSpringEvent event) {
        System.out.println("Current thread " + Thread.currentThread().getId());
        System.out.println("ThreadData: " + ThreadData.getUuid());
        System.out.println("Received spring custom event - " + event.getMessage());
    }
}
