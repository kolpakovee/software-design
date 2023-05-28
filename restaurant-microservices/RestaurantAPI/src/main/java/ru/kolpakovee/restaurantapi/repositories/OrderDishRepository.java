package ru.kolpakovee.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolpakovee.restaurantapi.entity.OrderDish;

public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
}
