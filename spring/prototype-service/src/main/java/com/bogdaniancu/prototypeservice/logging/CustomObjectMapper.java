package com.bogdaniancu.prototypeservice.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;

@Component
public class CustomObjectMapper {

    private Logger logger = LoggerFactory.getLogger(CustomObjectMapper.class);
    private ObjectMapper objectMapper;

    public CustomObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
            @Override
            public boolean hasIgnoreMarker(AnnotatedMember m) {
                return (_findAnnotation(m, NotLogged.class) != null);
            }});
    }

    public String objectToJson(Object object) {
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
}
