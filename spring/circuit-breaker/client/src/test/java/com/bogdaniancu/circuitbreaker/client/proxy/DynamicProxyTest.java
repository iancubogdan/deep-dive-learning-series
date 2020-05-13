//package com.bogdaniancu.circuitbreaker.client.proxy;
//
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DynamicProxyTest {
//
//
//    @Test
//    public void testProxy() {
//        Map proxiedMap = passthroughProxy(Map.class, new HashMap());
//
//        proxiedMap.put("hello", "world");
//        System.out.println(proxiedMap);
//    }
//
//    public static <T> T simpleProxy(Class<? extends T> iface, InvocationHandler handler, Class<?>...otherIfaces) {
//        Class<?>[] allInterfaces = Stream.concat(
//                Stream.of(iface),
//                Stream.of(otherIfaces)).distinct().toArray(Class[]::new);
//
//        return (T) Proxy.newProxyInstance(
//                iface.getClassLoader(),
//                allInterfaces,
//                handler);
//    }
//
//
//    public static <T> T passthroughProxy(Class<? extends T> iface, T target) {
//        return simpleProxy(iface, new DynamicInvocationHandler(target));
//    }
//
//    public void randomMethod() {
//        System.out.println("bla bla");
//    }
//
//}