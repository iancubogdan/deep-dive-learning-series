package com.bogdaniancu.prototypeservice.services;

import com.bogdaniancu.prototypeservice.data.FooData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FooServiceTest {

    @Autowired
    private FooService fooService;

    @Test
    void foo() {
        fooService.foo();
    }

    @Test
    void testFoo() {
        FooData fooData = new FooData();
        fooData.setPublicField1("public1");
        fooData.setPublicField2("public2");
        fooData.setPrivateField3("private3");
        fooData.setPublicField4("public4");

        fooService.foo(fooData);
    }
}