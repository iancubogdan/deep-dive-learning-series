package com.bogdaniancu.prototypeservice.helpers;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * https://www.callicoder.com/spring-boot-configuration-properties-example/
 */

@Data
//@Component //does not work with constructor binding
//@EnableConfigurationProperties //@SpringBootApplication is already annotated with this
@ConfigurationProperties(prefix = "app")
@ConstructorBinding
public class ConfigurationService {

    private String value1;
    private String value2;

    public ConfigurationService(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    //    private Monitoring monitoring;
//
//    @Data
//    public static class Monitoring {
//        private String value1;
//        private String value2;
//    }
}


