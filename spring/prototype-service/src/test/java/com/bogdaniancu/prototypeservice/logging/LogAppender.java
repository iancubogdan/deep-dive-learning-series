package com.bogdaniancu.prototypeservice.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class LogAppender extends AppenderBase<ILoggingEvent> {

    static List<ILoggingEvent> events = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        events.add(eventObject);
    }

    public static List<ILoggingEvent> getEvents() {
        return events;
    }

    public static void clearEvents() {
        events.clear();
    }
}
