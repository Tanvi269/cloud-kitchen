package com.cloud.kitchen.controller;

import com.cloud.kitchen.model.User;
import com.cloud.kitchen.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ADMIN LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> loginData,
            HttpSession session) {

        String email = loginData.get("email");
        String password = loginData.get("password");

        // Input validation
        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {

            return ResponseEntity.badRequest()
                    .body("Email and password are required");
        }

        User user = userRepository.findByEmail(email).orElse(null);

        // Check credentials
        if (user == null ||
                !passwordEncoder.matches(password, user.getPassword())) {

            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }

        // Admin authorization
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {

            return ResponseEntity.status(403)
                    .body("Access denied. Admin only.");
        }

        // Create session
        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("role", user.getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }

    // CHECK LOGIN
    @GetMapping("/check")
    public ResponseEntity<?> checkLogin(HttpSession session) {

        Object userId = session.getAttribute("userId");
        Object role = session.getAttribute("role");

        if (userId != null && "ADMIN".equals(role)) {
            return ResponseEntity.ok("Authenticated");
        }

        return ResponseEntity.status(401)
                .body("Not authenticated");
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {

        session.invalidate();

        return ResponseEntity.ok("Logout successful");
    }
}