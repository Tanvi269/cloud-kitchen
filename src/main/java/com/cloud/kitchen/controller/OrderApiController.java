package com.cloud.kitchen.controller;

import com.cloud.kitchen.model.Order;
import com.cloud.kitchen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderApiController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(HttpSession session) {
        com.cloud.kitchen.model.User user = (com.cloud.kitchen.model.User) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(orderRepository.findByUserId(user.getId()));
        }
        // If not logged in, they see no orders (or we could return 401)
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order, HttpSession session) {
        try {
            System.out.println(">>> RECEIVED ORDER: " + order);

            com.cloud.kitchen.model.User user = (com.cloud.kitchen.model.User) session.getAttribute("user");
            if (user != null) {
                order.setUserId(user.getId());
                if (order.getCustomerName() == null || order.getCustomerName().isEmpty()) {
                    order.setCustomerName(user.getName());
                }
            } else {
                return ResponseEntity.status(401).body("Error: Customer must be logged in to place an order.");
            }

            if (order.getStatus() == null || order.getStatus().isEmpty()) {
                order.setStatus("CONFIRMED");
            }

            Order savedOrder = orderRepository.save(order);
            return ResponseEntity.ok(savedOrder);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }

    // UPDATE ORDER STATUS FROM ADMIN
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

           order.setStatus(status);

// Start preparation timer when admin clicks Start Preparing
if ("PREPARING".equalsIgnoreCase(status)) {
    order.setOrderStartTime(System.currentTimeMillis());
}

Order updatedOrder = orderRepository.save(order);

            return ResponseEntity.ok(updatedOrder);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }
}