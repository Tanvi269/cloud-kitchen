package com.cloud.kitchen.controller;

import com.cloud.kitchen.model.User;
import com.cloud.kitchen.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody User customer) {
        try {
            if (userRepository.findByEmail(customer.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email already exists");
            }
            customer.setRole("CUSTOMER");
            User saved = userRepository.save(customer);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody User loginReq, HttpSession session) {
        Optional<User> userOpt = userRepository.findByEmail(loginReq.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(loginReq.getPassword()) && "CUSTOMER".equals(user.getRole())) {
                session.setAttribute("user", user);
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutCustomer(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/status")
    public ResponseEntity<?> checkStatus(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("loggedIn", true);
            response.put("user", user);
        } else {
            response.put("loggedIn", false);
        }
        return ResponseEntity.ok(response);
    }
}
