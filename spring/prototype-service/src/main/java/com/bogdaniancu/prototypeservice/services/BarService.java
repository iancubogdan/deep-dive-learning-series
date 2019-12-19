package com.bogdaniancu.prototypeservice.services;

import com.bogdaniancu.prototypeservice.logging.LogLevel;
import com.bogdaniancu.prototypeservice.logging.Logged;
import com.bogdaniancu.prototypeservice.logging.Monitored;
import com.bogdaniancu.prototypeservice.data.BarData;
import com.bogdaniancu.prototypeservice.data.FooData;
import org.springframework.stereotype.Service;

@Service
public class BarService {

    @Logged(loglevel = LogLevel.DEBUG)
    public void bar() {
//        System.out.println("Bar");
    }

    @Logged(loglevel = LogLevel.DEBUG)
    @Monitored
    public void bar(FooData fooData, BarData barData) {
//        System.out.println("Bar");
    }
}
