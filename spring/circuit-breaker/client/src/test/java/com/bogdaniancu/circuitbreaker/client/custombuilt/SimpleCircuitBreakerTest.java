package com.bogdaniancu.circuitbreaker.client.custombuilt;

import com.bogdaniancu.circuitbreaker.client.services.AdeleService;
import com.bogdaniancu.circuitbreaker.client.services.CallerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleCircuitBreakerTest {

    @Autowired
    private CallerService callerService;
    @Autowired
    private AdeleService adeleService;

    @Test
    public void testCaller() {
        callerService.call();
    }

    @Test
    public void persistentCaller() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            System.out.println("________________________________________________\n" + "Call #" + i );
            if (i == 3) {
                adeleService.setDoNotDisturb(true);
            }

            if (i == 13) {
                Thread.sleep(1002);
//                adeleService.setDoNotDisturb(false);
            }
            callerService.call();
        }
    }

}