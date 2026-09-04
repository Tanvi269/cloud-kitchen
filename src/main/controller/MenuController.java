package com.cloud.kitchen.controller;

import com.cloud.kitchen.model.FoodItem;
import com.cloud.kitchen.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private FoodItemRepository foodItemRepository;

    // Get all menu items
    @GetMapping
    public List<FoodItem> getAllMenuItems() {
        return foodItemRepository.findAll();
    }

    // Add a new menu item
    @PostMapping
    public FoodItem addMenuItem(@RequestBody FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }
}