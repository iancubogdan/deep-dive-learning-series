package com.bogdaniancu.spring.cloud.consumer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-producer",
//            url = "${feign.client.url}",
//            configuration = FeignClientConfiguration.class,
        fallback = ProducerFallback.class)
public interface ProducerClient {
    @RequestMapping(method = RequestMethod.GET, value = "/dummyData")
    String getDummyData();
}