package com.cloud.kitchen.controller;

import com.cloud.kitchen.model.User;
import com.cloud.kitchen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin-login";
    }

    @PostMapping("/admin/login")
    public String adminLoginSubmit(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null &&
            user.getPassword().equals(password) &&
            "ADMIN".equals(user.getRole())) {

            session.setAttribute("admin", user);
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "Invalid admin email or password");
        return "admin-login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        return "admin-dashboard";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}