package com.cloud.kitchen.controller;

import com.cloud.kitchen.model.Order;
import com.cloud.kitchen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    @Autowired
    private OrderRepository orderRepository;

    // Endpoint for customer status polling & admin viewing
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Endpoint for saving a new customer order from checkout
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("CONFIRMED");
        }
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    // Endpoint for updating order status from admin dashboard
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }
}