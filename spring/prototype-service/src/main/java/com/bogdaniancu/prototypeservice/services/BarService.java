package com.bogdaniancu.prototypeservice.services;

import com.bogdaniancu.prototypeservice.aspects.LogLevel;
import com.bogdaniancu.prototypeservice.aspects.Logged;
import com.bogdaniancu.prototypeservice.aspects.NotLogged;
import com.bogdaniancu.prototypeservice.data.BarData;
import com.bogdaniancu.prototypeservice.data.FooData;
import org.springframework.stereotype.Service;

@Service
public class BarService {

    @Logged(loglevel = LogLevel.DEBUG)
    public void bar() {
        System.out.println("Bar");
    }

    @Logged(loglevel = LogLevel.DEBUG)
    public void bar(FooData fooData, BarData barData) {
        System.out.println("Bar");
    }
}
