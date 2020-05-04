package com.bogdaniancu.circuitbreaker.client.custombuilt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceATest {

    @Autowired
    public ServiceA serviceA;

    @Test
    public void test() throws InterruptedException {
        serviceA.setFail(true);
        for (int i = 0; i < 12; i++) {
            if (i == 8) {
                Thread.sleep(1002);
                serviceA.setFail(false);
            }
            serviceA.call();
            System.out.println("__________________________________________\n");
        }
    }

}