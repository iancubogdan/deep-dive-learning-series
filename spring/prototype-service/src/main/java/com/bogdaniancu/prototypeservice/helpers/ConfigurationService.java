package com.bogdaniancu.prototypeservice.helpers;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "demo.prop")
public class ConfigurationService {

    private String value1;
    private String value2;
}
