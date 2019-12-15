package com.bogdaniancu.prototypeservice.services;

import com.bogdaniancu.prototypeservice.aspects.Logged;
import com.bogdaniancu.prototypeservice.aspects.NotLogged;
import com.bogdaniancu.prototypeservice.data.BarData;
import com.bogdaniancu.prototypeservice.data.FooData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class FooService {

    @Autowired
    private BarService barService;

    @Logged
    public void foo() {
        System.out.println("foo");
        barService.bar();
    }

    @Logged
    public void foo(@NotLogged FooData fooData) {
        BarData barData = new BarData();
        barData.setPublicField1("public1");
        barData.setPublicField2("public2");
        barData.setPrivateField3("private3");
        barData.setPublicField4("public4");

        System.out.println(fooData);
        barService.bar(fooData, barData);
    }
}
