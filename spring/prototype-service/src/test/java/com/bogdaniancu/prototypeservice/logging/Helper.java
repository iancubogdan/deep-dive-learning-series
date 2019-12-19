package com.bogdaniancu.prototypeservice.logging;

import org.springframework.stereotype.Component;

@Component
public class Helper {

    @Logged
    public void loggedMethod(FooData fooData) {
    }

    @Logged
    public void loggedMethodWithIgnoredDataClass(FooData fooData, BarData barData) {
    }

    @Logged
    public void loggedMethodWithIgnoredFirstArgument(@NotLogged FooData fooData1, FooData fooData2) {

    }

    @Logged
    public void loggedMethodWithIgnoredSecondArgument(FooData fooData1, @NotLogged FooData fooData) {

    }
}
