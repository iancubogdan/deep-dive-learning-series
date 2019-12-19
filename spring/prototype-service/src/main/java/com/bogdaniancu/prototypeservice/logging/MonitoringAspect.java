package com.bogdaniancu.prototypeservice.logging;


import com.bogdaniancu.prototypeservice.helpers.Uuid;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitoringAspect {

    private Logger logger = LoggerFactory.getLogger(MonitoringAspect.class);
    private final Uuid uuid;

    public MonitoringAspect(Uuid uuid) {
        this.uuid = uuid;
    }

    @Around("@annotation(com.bogdaniancu.prototypeservice.logging.Monitored)")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Monitored annotation = methodSignature.getMethod().getAnnotation(Monitored.class);
        long t0 = System.currentTimeMillis();

        Object returnedObject;
        try {
            returnedObject = joinPoint.proceed();
        } catch (Exception e) {
            //TODO
            long deltaMillis = System.currentTimeMillis() - t0;
            logger.error("Exception (): {}: {}",
                    e.getClass(), e.getMessage());
            throw e;
        }
        long deltaMillis = System.currentTimeMillis() - t0;
        logger.debug( "Method {}.{} took {} milliseconds to execute uuid: {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                deltaMillis,
                uuid.getUuid());
//                ThreadData.getUuid());

//        logger.debug("Call took {} milliseconds", deltaMillis);
        return returnedObject;
    }

}
