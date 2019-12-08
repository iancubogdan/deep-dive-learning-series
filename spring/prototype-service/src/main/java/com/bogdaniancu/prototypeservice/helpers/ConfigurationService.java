package com.bogdaniancu.prototypeservice.helpers;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * https://www.callicoder.com/spring-boot-configuration-properties-example/
 */

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class ConfigurationService {

    private String value1;
    private String value2;

    private Monitoring monitoring;

    @Data
    public static class Monitoring {
        private String value1;
        private String value2;
    }
}
