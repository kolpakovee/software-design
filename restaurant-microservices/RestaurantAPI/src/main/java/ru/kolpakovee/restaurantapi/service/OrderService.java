package ru.kolpakovee.restaurantapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolpakovee.restaurantapi.dto.OrderDto;
import ru.kolpakovee.restaurantapi.entity.Order;
import ru.kolpakovee.restaurantapi.factories.OrderFactory;
import ru.kolpakovee.restaurantapi.repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public void createOrder(OrderDto orderDto) {
        Order order = orderFactory.makeOrder(orderDto);


    }
}
