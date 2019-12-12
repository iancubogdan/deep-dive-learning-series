package com.bogdaniancu.prototypeservice.helpers;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Uuid {

    private String uuid;

    public Uuid() {
        uuid = UUID.randomUUID().toString();
    }
}
