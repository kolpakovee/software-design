package ru.kolpakovee.restaurantapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolpakovee.restaurantapi.dto.DishDto;
import ru.kolpakovee.restaurantapi.entity.Dish;
import ru.kolpakovee.restaurantapi.service.DishService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant/dish")
public class DishController {
    private final DishService dishService;

    @GetMapping("/menu")
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish>  menu = dishService.findAll();
        if (menu.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable("id") Long id,
                                            @RequestHeader("Role") String role) {
        Dish dish = dishService.getDishById(id, role);
        return ResponseEntity.ok(dish);
    }

    @PostMapping("/create")
    public ResponseEntity<Dish> createDish(@RequestBody @Valid DishDto dishDto,
                                           @RequestHeader("Role") String role) {
        Dish createdDish = dishService.createDish(dishDto, role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable("id") Long id, @RequestBody @Valid DishDto dishDto,
                                           @RequestHeader("Role") String role) {
        Dish updatedDish = dishService.updateDish(id, dishDto, role);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable("id") Long id,
                                             @RequestHeader("Role") String role){
        dishService.deleteDish(id, role);
        return ResponseEntity.noContent().build();
    }
}
