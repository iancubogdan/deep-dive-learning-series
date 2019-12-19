package com.bogdaniancu.prototypeservice.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Component
public class ArgumentResolver {

    private CustomObjectMapper mapper;

    public ArgumentResolver(CustomObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Map<String, String> createArgumentList(ProceedingJoinPoint joinPoint, MethodSignature methodSignature) {
        List<Object> notIgnoredArguments = notIgnoredArguments(methodSignature, joinPoint.getArgs());
        return notIgnoredArguments.stream()
                .filter(this::hasIgnoredAnnotation)
                .collect(groupingBy(it -> it.getClass().getSimpleName(),
                        mapping(mapper::objectToJson, joining(", "))));
    }

    private boolean hasIgnoredAnnotation(Object object) {
        return object.getClass().getAnnotation(NotLogged.class) == null;
    }

    private List<Object> notIgnoredArguments(MethodSignature methodSignature, Object[] arguments) {
        List<Object> args = new ArrayList<>();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        for (int i = 0; i< parameters.length; i++) {
            if (parameters[i].getAnnotation(NotLogged.class) == null) {
                args.add(arguments[i]);
            }
        }
        return args;
    }
}
