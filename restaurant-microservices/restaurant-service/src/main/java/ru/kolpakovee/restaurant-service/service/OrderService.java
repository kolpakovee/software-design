package ru.kolpakovee.restaurantapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolpakovee.restaurantapi.dto.OrderDto;
import ru.kolpakovee.restaurantapi.entity.Order;
import ru.kolpakovee.restaurantapi.entity.OrderDish;
import ru.kolpakovee.restaurantapi.exceptions.NotFoundDishException;
import ru.kolpakovee.restaurantapi.exceptions.NotFoundEmailException;
import ru.kolpakovee.restaurantapi.exceptions.OrderNotFoundException;
import ru.kolpakovee.restaurantapi.repositories.DishRepository;
import ru.kolpakovee.restaurantapi.repositories.OrderDishRepository;
import ru.kolpakovee.restaurantapi.repositories.OrderRepository;
import ru.kolpakovee.restaurantapi.repositories.UserRepository;

import static ru.kolpakovee.restaurantapi.factories.OrderDishFactory.makeOrderDish;
import static ru.kolpakovee.restaurantapi.factories.OrderFactory.makeOrder;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final OrderDishRepository orderDishRepository;

    public Order createOrder(OrderDto orderDto, String email) {
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new NotFoundEmailException("Email not found.");
        }

        Order order = makeOrder(orderDto, user.get());

        orderRepository.save(order);

        for (var od : orderDto.getOrderDishList()) {
            var dish = dishRepository.findById(od.getDishId());

            if (dish.isEmpty()) {
                throw new NotFoundDishException("Dish not found.");
            }

            OrderDish orderDish = makeOrderDish(od, dish.get(), order);
            orderDishRepository.save(orderDish);
        }

        return order;
    }

    public Order getOrderById(Long orderId) {
        var order = orderRepository.findById(orderId);

        if (order.isEmpty()) throw new OrderNotFoundException("Order not found.");

        return order.get();
    }
}
