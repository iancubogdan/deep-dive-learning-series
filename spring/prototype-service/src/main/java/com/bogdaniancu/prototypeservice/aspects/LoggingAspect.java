package com.bogdaniancu.prototypeservice.aspects;

import com.bogdaniancu.prototypeservice.helpers.Uuid;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private Uuid uuid;

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.bogdaniancu.prototypeservice.aspects.Logged)")
    public void advice(ProceedingJoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Logged annotation = methodSignature.getMethod().getAnnotation(Logged.class);

        annotation.loglevel().log(logger, "Calling " + joinPoint.getTarget().getClass() + " "
                + joinPoint.getSignature().getName() + " with identifier: " + uuid.getUuid());

//        System.out.println("Calling " + joinPoint.getTarget().getClass() + " "
//                + joinPoint.getSignature().getName() + " with identifier: " + uuid.getUuid());

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after method");
    }
}
