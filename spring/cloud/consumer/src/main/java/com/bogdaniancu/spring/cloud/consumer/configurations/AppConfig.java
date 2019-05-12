package com.bogdaniancu.spring.cloud.consumer.configurations;

import org.springframework.context.annotation.Configuration;

/**
 * As the Hystrix capabilities are transparently injected as AOP advice, we have to adjust the order in which the advice is stacked,
 * in case if we have other advice like Spring’s transactional advice. Here we have adjusted the Spring’s transaction AOP advice to
 * have lower precedence than Hystrix AOP advice:
 */


//@Configuration
//@EnableTransactionManagement
public class AppConfig {

//    @Bean
//    @Primary
//    @Order(value= Ordered.HIGHEST_PRECEDENCE)
//    public HystrixCommandAspect hystrixAspect() {
//        return new HystrixCommandAspect();
//    }
}
