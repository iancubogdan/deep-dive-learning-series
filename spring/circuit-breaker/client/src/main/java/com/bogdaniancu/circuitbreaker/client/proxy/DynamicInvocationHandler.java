package com.bogdaniancu.circuitbreaker.client.proxy;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicInvocationHandler implements InvocationHandler {

    private final Object target;

    public DynamicInvocationHandler(Object target) {
        this.target = target;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(
            DynamicInvocationHandler.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Invoked method: {}", method.getName());
        Object result = method.invoke(proxy, args);
        return result;
    }
}
