package com.bogdaniancu.prototypeservice.logging;

import com.bogdaniancu.prototypeservice.helpers.Uuid;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Map;



@Aspect
@Component
public class LoggingAspect {

    private final Uuid uuid;
    private ArgumentResolver argumentResolver;

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect(Uuid uuid, ArgumentResolver argumentResolver) {
        this.uuid = uuid;
        this.argumentResolver = argumentResolver;
    }


    @Around("@annotation(com.bogdaniancu.prototypeservice.logging.Logged)")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Logged annotation = methodSignature.getMethod().getAnnotation(Logged.class);

        Map<String, String> argsList = argumentResolver.createArgumentList(joinPoint, methodSignature);
        Object returnedObject;

        annotation.loglevel().log(logger, "Invoking {}.{} with args: {} uuid: {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                argsList,
                uuid.getUuid());
//                ThreadData.getUuid());
        try {
            returnedObject = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Exception (): {}: {}",
                    e.getClass(), e.getMessage());
            throw e;
        }
        return returnedObject;
    }



}
