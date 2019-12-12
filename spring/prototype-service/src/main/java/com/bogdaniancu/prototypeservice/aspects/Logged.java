package com.bogdaniancu.prototypeservice.aspects;


/**
 * https://makeinjava.com/logging-aspect-restful-web-service-spring-aop-request-response/
 */

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
//@Service
//@Validated
//@Transactional(propagation = Propagation.REQUIRED, timeout = 59)
public @interface Logged {

    LogLevel loglevel() default LogLevel.INFO;
}
