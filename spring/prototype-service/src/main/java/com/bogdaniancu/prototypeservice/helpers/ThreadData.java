package com.bogdaniancu.prototypeservice.helpers;

import java.util.UUID;

public class ThreadData {

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> UUID.randomUUID().toString());

    public static String getUuid() {
        return threadLocal.get();
    }
}
