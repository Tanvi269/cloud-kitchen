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
import com.cloud.kitchen.model.FoodItem;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;


    @GetMapping("/admin")
    public String adminRoot() {
        return "redirect:/admin-login.html";
    }


    @GetMapping("/admin/portal-login")
    public String adminLogin() {
        return "redirect:/admin-login.html";
    }


    @PostMapping("/admin/login")
    public String adminLoginSubmit(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            // Admin login
            if ("admin@cloudkitchen.com".equals(email)
                    && "admin123".equals(password)) {

                User dummyAdmin =
                        userRepository.findByEmail(email).orElse(new User());

                dummyAdmin.setEmail(email);
                dummyAdmin.setRole("ADMIN");

                session.setAttribute("admin", dummyAdmin);

                return "redirect:/admin/dashboard";
            }


            User user =
                    userRepository.findByEmail(email).orElse(null);

            if (user != null
                    && user.getPassword() != null
                    && user.getPassword().equals(password)
                    && "ADMIN".equals(user.getRole())) {

                session.setAttribute("admin", user);

                return "redirect:/admin/dashboard";
            }


            model.addAttribute(
                    "error",
                    "Invalid admin email or password"
            );

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    "Database error: " + e.getMessage()
            );
        }

        return "admin-login";
    }


    @GetMapping("/admin/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/portal-login";
        }

        List<Order> orders =
                orderRepository.findAll();

        model.addAttribute("orders", orders);


        List<FoodItem> foodItems =
                foodItemRepository.findAll();

        model.addAttribute("foodItems", foodItems);


        return "admin-dashboard";
    }


    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/admin/portal-login";
    }


    // UPDATE ORDER STATUS
    @PostMapping("/admin/orders/update-status")
    public String updateOrderStatusAdmin(
            @RequestParam Long orderId,
            @RequestParam String status,
            HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/portal-login";
        }

        Order order =
                orderRepository.findById(orderId).orElse(null);

        if (order != null) {

            order.setStatus(status);


           if ("PREPARING".equalsIgnoreCase(status)) {

    // Fixed total preparation time
    order.setPreparationTime(30);

    // Start with 30 minutes remaining
    order.setRemainingTime(30);

    order.setOrderStartTime(
            System.currentTimeMillis()
    );
}


            orderRepository.save(order);
        }

        return "redirect:/admin/dashboard";
    }


    @PostMapping("/admin/orders/update-remaining-time")
public String updateRemainingTime(
        @RequestParam Long orderId,
        @RequestParam int remainingTime,
        HttpSession session) {

    if (session.getAttribute("admin") == null) {
        return "redirect:/admin/portal-login";
    }

    Order order =
            orderRepository.findById(orderId).orElse(null);

    if (order != null) {

        if (remainingTime < 0) {
            remainingTime = 0;
        }

        if (remainingTime > 30) {
            remainingTime = 30;
        }

        order.setRemainingTime(remainingTime);

        orderRepository.save(order);
    }

    return "redirect:/admin/dashboard";
}


    // FOOD AVAILABILITY
    @PostMapping("/admin/food/update-availability")
    public String updateFoodAvailability(
            @RequestParam Long foodId,
            @RequestParam boolean available,
            HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/portal-login";
        }


        foodItemRepository.findById(foodId).ifPresent(food -> {

            food.setAvailable(available);

            foodItemRepository.save(food);
        });


        return "redirect:/admin/dashboard";
    }
}