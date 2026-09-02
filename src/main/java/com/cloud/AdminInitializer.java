package com.cloud.kitchen.config;

import com.cloud.kitchen.model.User;
import com.cloud.kitchen.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository) {
        return args -> {
            String adminEmail = "admin@cloudkitchen.com";
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("Default admin user created: " + adminEmail);
            }
        };
    }
}