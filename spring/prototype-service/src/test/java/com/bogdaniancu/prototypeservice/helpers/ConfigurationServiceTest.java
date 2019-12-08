package com.bogdaniancu.prototypeservice.helpers;

import com.bogdaniancu.prototypeservice.services.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConfigurationServiceTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void demoTest() {
        demoService.demo();
    }

}