package com.cloud.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cloud.kitchen")
@EnableJpaRepositories("com.cloud.kitchen.repository")
public class CloudKitchenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudKitchenApplication.class, args);
    }

}