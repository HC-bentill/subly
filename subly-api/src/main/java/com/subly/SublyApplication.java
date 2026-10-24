package com.subly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Lives in the root package {@code com.subly} so component, entity and repository
 * scanning picks up every module (domain, application, infrastructure, api).
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class SublyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SublyApplication.class, args);
    }
}
