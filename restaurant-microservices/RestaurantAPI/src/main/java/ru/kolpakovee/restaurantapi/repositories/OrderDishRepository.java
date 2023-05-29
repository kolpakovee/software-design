package ru.kolpakovee.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolpakovee.restaurantapi.entity.Dish;
import ru.kolpakovee.restaurantapi.entity.OrderDish;

import java.util.List;

public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
    List<OrderDish> findAllByDish(Dish dish);
}
