package com.cloud.kitchen.controller;

import com.cloud.kitchen.model.FoodItem;
import com.cloud.kitchen.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class FoodApiController {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @GetMapping
    public List<FoodItem> getMenu() {
        return foodItemRepository.findAll();
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<?> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        FoodItem item = foodItemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setAvailable(available);
            foodItemRepository.save(item);
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }
}
