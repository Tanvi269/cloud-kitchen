package com.cloud.kitchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disables CSRF so your login and order forms submit smoothly
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/menu", "/cart/**", "/orders/**", "/css/**", "/js/**", "/images/**", "/error", "/admin/**", "/admin/login", "/admin/dashboard").permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}