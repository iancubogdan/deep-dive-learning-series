package com.bogdaniancu.prototypeservice.aspects;

import com.bogdaniancu.prototypeservice.helpers.ThreadData;
import com.bogdaniancu.prototypeservice.helpers.Uuid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.*;


@Aspect
@Component
public class LoggingAspect {

    private final Uuid uuid;

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect(Uuid uuid) {
        this.uuid = uuid;
    }

    @Around("@annotation(com.bogdaniancu.prototypeservice.aspects.Logged)")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Logged annotation = methodSignature.getMethod().getAnnotation(Logged.class);

        Map<String, String> argsList = createArgumentList(joinPoint, methodSignature);
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

    private Map<String, String> createArgumentList(ProceedingJoinPoint joinPoint, MethodSignature methodSignature) {
        return Arrays.stream(joinPoint.getArgs())
                .filter(this::hasIgnoredAnnotation)
                .filter(arg -> (this.hasIgnoredParameterAnnotation(methodSignature, arg)))
                .collect(groupingBy(it -> it.getClass().getSimpleName(),
                        mapping(this::objectToJson, joining(", "))));
    }

    private boolean hasIgnoredAnnotation(Object object) {
        return object.getClass().getAnnotation(NotLogged.class) == null;
    }

    private boolean hasIgnoredParameterAnnotation(MethodSignature methodSignature, Object arg) {
       return Arrays.stream(methodSignature.getMethod().getParameters()).anyMatch(
               parameter -> (parameter.getType().getSimpleName().equals(arg.getClass().getSimpleName())) &&
                       (parameter.getAnnotation(NotLogged.class) == null)
       );
    }


    private String objectToJson(Object object) {
        ObjectMapper objectMapper = getCustomMapper();

        try {
            if (object instanceof ServletOutputStream) {
                return "<ServletOutputStream>";
            } else if (object instanceof InputStream) {
                return "<InputStream>";
            } else {
                return objectMapper.writeValueAsString(object);
            }
        } catch (JsonProcessingException e) {
            logger.warn("Could not serialize as JSON (stacktrace on TRACE): " + e);
            logger.trace("Cannot serialize value: " + e, e);

            return "<ERR>";
        }
    }

    private ObjectMapper getCustomMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
            @Override
            public boolean hasIgnoreMarker(AnnotatedMember m) {
                if(_findAnnotation(m, NotLogged.class) != null){
                    return true;
                } else {
                    return false;
                }
            }});
        return objectMapper;
    }

}
