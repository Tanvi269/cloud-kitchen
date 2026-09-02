package com.cloud.kitchen;

import com.cloud.kitchen.model.User;
import com.cloud.kitchen.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudKitchenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudKitchenApplication.class, args);
    }

    @Bean
    
CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@kitchen.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@kitchen.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("Default admin user created successfully!");
            }
        };
    }
}