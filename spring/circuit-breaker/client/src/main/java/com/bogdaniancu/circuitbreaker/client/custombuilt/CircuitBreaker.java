package com.bogdaniancu.circuitbreaker.client.custombuilt;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;

@Component
@Data
public class CircuitBreaker {

    enum State {
        Open, Closed, HalfOpen
    }

    private static int FAILURE_THRESHOLD = 5;
    private static int RESET_TIMEOUT = 1000;  //in milliseconds
    private int failureCount = 0;
    private long lastFailTime;

    public void recordFailure() {
        lastFailTime = new Date().getTime();
        failureCount++;
    }

    public void reset() {
        failureCount = 0;
        lastFailTime = 0;
    }

    public State getState() {
        if (failureCount >= FAILURE_THRESHOLD &&
                (new Date().getTime() - lastFailTime) > RESET_TIMEOUT) {
            return State.HalfOpen;
        }
        if (failureCount >= FAILURE_THRESHOLD) {
            return State.Open;
        }
        return State.Closed;
    }
}
