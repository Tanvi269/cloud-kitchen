package com.cloud.kitchen.controller;
import com.cloud.kitchen.repository.FoodItemRepository;
import com.cloud.kitchen.model.Order;
import com.cloud.kitchen.model.User;
import com.cloud.kitchen.repository.OrderRepository;
import com.cloud.kitchen.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/admin")
    public String adminRoot() {
        return "redirect:/admin/portal-login";
    }

   @GetMapping("/admin/portal-login")
    public String adminLogin() {
        return "admin-login"; 
    }

    @PostMapping("/admin/login")
    public String adminLoginSubmit(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {
            // Viva Quick Bypass: Instantly allows admin login without failing on hashes
            if ("admin@cloudkitchen.com".equals(email) && "admin123".equals(password)) {
                // Find or create a dummy user object for the session
                User dummyAdmin = userRepository.findByEmail(email).orElse(new User());
                dummyAdmin.setEmail(email);
                dummyAdmin.setRole("ADMIN");
                
                session.setAttribute("admin", dummyAdmin);
                return "redirect:/admin/dashboard";
            }

            User user = userRepository.findByEmail(email).orElse(null);

            if (user != null &&
                user.getPassword() != null &&
                user.getPassword().equals(password) &&
                "ADMIN".equals(user.getRole())) {

                session.setAttribute("admin", user);
                return "redirect:/admin/dashboard";
            }

            model.addAttribute("error", "Invalid admin email or password");
        } catch (Exception e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
        }
        
        return "admin-login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);

        return "admin-dashboard"; 
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

   @PostMapping("/admin/orders/update-status")
    public String updateOrderStatusAdmin(@RequestParam Long orderId, @RequestParam String status, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }
        return "redirect:/admin/dashboard";
    }
}