package com.bogdaniancu.circuitbreaker.client.custombuilt.initial;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
//
//@Component
//@Data
public class CircuitBreaker {

    enum State {
        Open, Closed
    }

    private static int FAILURE_THRESHOLD = 5;
    private int failureCount = 0;
    private LocalDateTime lastFailTime;

    public void recordFailure() {
        failureCount++;
    }

    public void reset() {
        failureCount = 0;
    }

    public State getState() {
        if (failureCount > FAILURE_THRESHOLD) {
            return State.Open;
        }
        return State.Closed;
    }
}
