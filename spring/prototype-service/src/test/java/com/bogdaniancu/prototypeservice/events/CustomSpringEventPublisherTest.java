package com.bogdaniancu.prototypeservice.events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomSpringEventPublisherTest {

    @Autowired
    private CustomSpringEventPublisher publisher;

    @Test
    void doStuffAndPublishEvent() {
        publisher.doStuffAndPublishEvent("My message");
    }
}