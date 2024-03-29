package com.bogdaniancu.prototypeservice.services;

import com.bogdaniancu.prototypeservice.logging.Logged;
import com.bogdaniancu.prototypeservice.helpers.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private ConfigurationService configurationService;

    @Logged
    public void demo() {
        System.out.println(configurationService.getValue1());
        System.out.println(configurationService.getValue2());
//
//        System.out.println(configurationService.getMonitoring().getValue1());
//        System.out.println(configurationService.getMonitoring().getValue2());
    }
}
