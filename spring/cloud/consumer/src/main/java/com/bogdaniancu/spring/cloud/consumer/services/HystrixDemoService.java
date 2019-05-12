package com.bogdaniancu.spring.cloud.consumer.services;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixDemoService {

    @HystrixCommand(fallbackMethod = "defaultDummyData")
    public String getDummyData() {
        return new RestTemplate().getForObject("http://localhost:8099/dummyData", String.class);
    }

    public String defaultDummyData() {
        return "default data";
    }
}