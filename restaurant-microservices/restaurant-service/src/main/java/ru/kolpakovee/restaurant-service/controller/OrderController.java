package ru.kolpakovee.restaurantapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolpakovee.restaurantapi.dto.OrderDto;
import ru.kolpakovee.restaurantapi.entity.Order;
import ru.kolpakovee.restaurantapi.pojo.OrderResponse;
import ru.kolpakovee.restaurantapi.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderDto orderDto,
                                                     @RequestHeader("Email") String email) {
        Order order = orderService.createOrder(orderDto, email);
        return ResponseEntity.ok(new OrderResponse(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long orderId){
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(new OrderResponse(order));
    }
}
