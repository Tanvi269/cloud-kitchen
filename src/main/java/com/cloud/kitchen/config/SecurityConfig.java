package com.cloud.kitchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Using NoOpPasswordEncoder so passwords are stored and compared as plain text.
    // For production, switch to BCryptPasswordEncoder and hash passwords on register.
    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Static pages
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/admin.html",
                    "/admin-login.html",
                    "/images/**",
                    "/css/**",
                    "/js/**"
                ).permitAll()

                // Admin controller URLs
                .requestMatchers("/admin/**").permitAll()

                // Customer auth APIs (register, login, logout, status)
                .requestMatchers("/api/customer/**").permitAll()

                // Customer can place and view orders
                .requestMatchers("/api/orders", "/api/orders/**").permitAll()

                // Customer can view menu
                .requestMatchers("/api/menu", "/api/menu/**").permitAll()

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