package com.cloud.kitchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/",
                    "/index.html",
                    "/admin-login.html",
                    "/images/**",
                    "/css/**",
                    "/js/**"
                ).permitAll()

                // Allow all admin URLs
                .requestMatchers("/admin/**").permitAll()

                // Custom login APIs
                .requestMatchers(
                    "/api/auth/login",
                    "/api/auth/logout",
                    "/api/auth/check"
                ).permitAll()

                // Customer can place orders
                .requestMatchers("/api/orders").permitAll()

                // Admin status update
                .requestMatchers("/api/orders/*/status").permitAll()

                // H2 console
                .requestMatchers("/h2-console/**").permitAll()

                .anyRequest().authenticated()
            )

            .headers(headers ->
                headers.frameOptions(frame -> frame.disable())
            );

        return http.build();
    }
}