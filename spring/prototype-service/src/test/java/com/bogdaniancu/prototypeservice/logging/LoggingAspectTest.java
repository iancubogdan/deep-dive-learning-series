package com.bogdaniancu.prototypeservice.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoggingAspectTest {

    @Autowired
    private Helper helper;

    @BeforeEach
    public void init() {
        LogAppender.clearEvents();
    }

    @Test
    public void shouldLogMethod() {
        FooData fooData = createFooData();

        helper.loggedMethod(fooData);
        List<ILoggingEvent> loggingEvents = LogAppender.getEvents();
        assertTrue(loggingEvents != null && !loggingEvents.isEmpty());
        assertTrue(loggingEvents.get(0).getFormattedMessage().contains("FooData"));
        assertTrue(loggingEvents.get(0).getFormattedMessage().contains("publicFoo1"));
    }

    @Test
    public void shouldNotLogPrivateFields() {
        FooData fooData = createFooData();

        helper.loggedMethod(fooData);
        List<ILoggingEvent> loggingEvents = LogAppender.getEvents();
        assertTrue(loggingEvents != null && !loggingEvents.isEmpty());
        assertTrue(loggingEvents.get(0).getFormattedMessage().contains("FooData"));
        assertTrue(!loggingEvents.get(0).getFormattedMessage().contains("private"));
    }

    @Test
    public void shouldNotLogIgnoredClasses() {
        FooData fooData = createFooData();
        BarData barData = createBarData();

        helper.loggedMethodWithIgnoredDataClass(fooData, barData);
        List<ILoggingEvent> loggingEvents = LogAppender.getEvents();
        assertTrue(loggingEvents != null && !loggingEvents.isEmpty());
        assertTrue(loggingEvents.get(0).getFormattedMessage().contains("FooData"));
        assertTrue(!loggingEvents.get(0).getFormattedMessage().contains("private"));
        assertTrue(!loggingEvents.get(0).getFormattedMessage().contains("BarData"));
    }

    @Test
    public void shouldNotLogIgnoredArguments() {
        FooData fooData1 = createFooData();
        FooData fooData2 = createAnotherFooData();

        helper.loggedMethodWithIgnoredFirstArgument(fooData1, fooData2);
        List<ILoggingEvent> loggingEvents = LogAppender.getEvents();
        assertTrue(loggingEvents != null && !loggingEvents.isEmpty());
        assertTrue(loggingEvents.get(0).getFormattedMessage().contains("publicFoo5"));
        assertTrue(!loggingEvents.get(0).getFormattedMessage().contains("publicFoo1"));
        LogAppender.clearEvents();

        helper.loggedMethodWithIgnoredSecondArgument(fooData1, fooData2);
        loggingEvents = LogAppender.getEvents();
        assertTrue(loggingEvents != null && !loggingEvents.isEmpty());
        assertTrue(loggingEvents.get(0).getFormattedMessage().contains("publicFoo1"));
        assertTrue(!loggingEvents.get(0).getFormattedMessage().contains("publicFoo5"));
    }

    private FooData createAnotherFooData() {
        FooData fooData = new FooData();
        fooData.setPublicField1("publicFoo5");
        fooData.setPublicField2("publicFoo6");
        fooData.setPrivateField3("privateFoo7");
        fooData.setPublicField4("publicFoo8");
        return fooData;
    }

    private FooData createFooData() {
        FooData fooData = new FooData();
        fooData.setPublicField1("publicFoo1");
        fooData.setPublicField2("publicFoo2");
        fooData.setPrivateField3("privateFoo3");
        fooData.setPublicField4("publicFoo4");
        return fooData;
    }

    private BarData createBarData() {
        BarData barData = new BarData();
        barData.setPublicField1("publicBar1");
        barData.setPublicField2("publicBar2");
        barData.setPrivateField3("privateBar3");
        barData.setPublicField4("publicBar4");
        return barData;
    }
}