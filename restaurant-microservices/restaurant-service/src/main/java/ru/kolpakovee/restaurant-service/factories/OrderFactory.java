package ru.kolpakovee.restaurantapi.factories;

import org.springframework.stereotype.Component;
import ru.kolpakovee.restaurantapi.dto.OrderDto;
import ru.kolpakovee.restaurantapi.entity.Order;
import ru.kolpakovee.restaurantapi.entity.User;
import ru.kolpakovee.restaurantapi.enums.OrderStatus;

@Component
public class OrderFactory {
    public static Order makeOrder(OrderDto orderDto, User user){
        Order order = new Order();

        order.setSpecialRequests(orderDto.getSpecialRequests());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(user);

        return order;
    }
}
