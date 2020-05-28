package com.bogdaniancu.circuitbreaker.client.custombuilt;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class SimpleCircuitBreaker {

    enum State {
        Open, Closed, HalfOpen
    }

    private static int FAILURE_THRESHOLD = 3;
    private static int RESET_TIMEOUT = 1000;
    private int failureCount = 0;
    private long lastFailTime = Long.MAX_VALUE;

    public void recordFailure() {
        lastFailTime = new Date().getTime();
        failureCount++;
    }

    public void reset() {
        failureCount = 0;
        lastFailTime = Long.MAX_VALUE;
    }

    public State getState() {
        if (failureCount > FAILURE_THRESHOLD &&
                (new Date().getTime() - lastFailTime) > RESET_TIMEOUT) {
            return State.HalfOpen;
        }

        if (failureCount > FAILURE_THRESHOLD) {
            return State.Open;
        }
        return State.Closed;
    }

    public boolean isOpen() {
        return getState().equals(State.Open);
    }

    public boolean isClosed() {
        return getState().equals(State.Closed);
    }

    public boolean isHalfOpen() {
        return getState().equals((State.HalfOpen));
    }
}
