package ru.kolpakovee.restaurantapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolpakovee.restaurantapi.dto.OrderDto;
import ru.kolpakovee.restaurantapi.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestParam OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }

    @GetMapping("/test")
    public String test(@RequestHeader("Role") String role){
        return role;
    }
}
