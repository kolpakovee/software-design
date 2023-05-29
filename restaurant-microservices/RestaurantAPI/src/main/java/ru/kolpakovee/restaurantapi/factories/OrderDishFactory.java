package ru.kolpakovee.restaurantapi.factories;

import ru.kolpakovee.restaurantapi.dto.OrderDishDto;
import ru.kolpakovee.restaurantapi.entity.Dish;
import ru.kolpakovee.restaurantapi.entity.Order;
import ru.kolpakovee.restaurantapi.entity.OrderDish;

public class OrderDishFactory {
    public static OrderDish makeOrderDish(OrderDishDto orderDishDto, Dish dish, Order order) {
        OrderDish orderDish = new OrderDish();

        orderDish.setDish(dish);
        orderDish.setQuantity(orderDishDto.getQuantity());
        orderDish.setPrice(orderDishDto.getPrice());
        orderDish.setOrder(order);

        return orderDish;
    }
}
