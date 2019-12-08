package com.bogdaniancu.prototypeservice.services;

import com.bogdaniancu.prototypeservice.helpers.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private ConfigurationService configurationService;

    public void demo() {
        System.out.println(configurationService.getValue1());
        System.out.println(configurationService.getValue2());
    }
}
