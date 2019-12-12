package com.bogdaniancu.prototypeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PrototypeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrototypeServiceApplication.class, args);
	}

}
