package com.cloud.kitchen.config;

import com.cloud.kitchen.model.FoodItem;
import com.cloud.kitchen.repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadFoodItems(FoodItemRepository repository) {

        return args -> {

            if (repository.count() == 0) {

                FoodItem biryani = new FoodItem();
                biryani.setName("Chicken Biryani");
                biryani.setDescription("Fragrant rice with delicious chicken.");
                biryani.setPrice(220);
                biryani.setCategory("Main Course");
                biryani.setImageUrl("images/biryani.png");
                biryani.setAvailable(true);
                repository.save(biryani);

                FoodItem pizza = new FoodItem();
                pizza.setName("Pizza");
                pizza.setDescription("Freshly baked cheesy pizza.");
                pizza.setPrice(250);
                pizza.setCategory("Fast Food");
                pizza.setImageUrl("images/pizza.png");
                pizza.setAvailable(true);
                repository.save(pizza);

                FoodItem burger = new FoodItem();
                burger.setName("Classic Burger");
                burger.setDescription("Juicy burger with fresh vegetables.");
                burger.setPrice(150);
                burger.setCategory("Fast Food");
                burger.setImageUrl("images/burger.png");
                burger.setAvailable(true);
                repository.save(burger);

                FoodItem pasta = new FoodItem();
                pasta.setName("Italian Pasta");
                pasta.setDescription("Creamy and delicious pasta.");
                pasta.setPrice(180);
                pasta.setCategory("Main Course");
                pasta.setImageUrl("images/pasta.png");
                pasta.setAvailable(true);
                repository.save(pasta);

                FoodItem friedRice = new FoodItem();
                friedRice.setName("Fried Rice");
                friedRice.setDescription("Fresh vegetables tossed with rice.");
                friedRice.setPrice(160);
                friedRice.setCategory("Main Course");
                friedRice.setImageUrl("images/fried-rice.png");
                friedRice.setAvailable(true);
                repository.save(friedRice);

                FoodItem paneer = new FoodItem();
                paneer.setName("Paneer Tikka");
                paneer.setDescription("Soft paneer with delicious spices.");
                paneer.setPrice(190);
                paneer.setCategory("Starter");
                paneer.setImageUrl("images/paneer-tikka.png");
                paneer.setAvailable(true);
                repository.save(paneer);

                System.out.println("Food items inserted successfully!");

            }
        };
    }
}