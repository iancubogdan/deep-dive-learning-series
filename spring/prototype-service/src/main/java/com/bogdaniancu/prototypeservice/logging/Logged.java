package com.bogdaniancu.prototypeservice.logging;


/**
 * https://makeinjava.com/logging-aspect-restful-web-service-spring-aop-request-response/
 */


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logged {

    LogLevel loglevel() default LogLevel.INFO;
}
