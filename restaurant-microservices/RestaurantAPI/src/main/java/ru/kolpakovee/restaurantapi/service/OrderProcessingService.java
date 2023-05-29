package ru.kolpakovee.restaurantapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.kolpakovee.restaurantapi.entity.Order;
import ru.kolpakovee.restaurantapi.enums.OrderStatus;
import ru.kolpakovee.restaurantapi.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderProcessingService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderProcessingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void processPendingOrders() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.PENDING);

        if (orders.isEmpty()) return;

        orders.get(0).setStatus(OrderStatus.IN_PROCESS);
        orders.get(0).setUpdatedAt(LocalDateTime.now());

        orderRepository.save(orders.get(0));
    }

    @Scheduled(fixedDelay = 120000)
    public void processCookingOrders() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.IN_PROCESS);

        if (orders.isEmpty()) return;

        orders.get(0).setStatus(OrderStatus.COMPLETED);
        orders.get(0).setUpdatedAt(LocalDateTime.now());

        orderRepository.save(orders.get(0));
    }
}
