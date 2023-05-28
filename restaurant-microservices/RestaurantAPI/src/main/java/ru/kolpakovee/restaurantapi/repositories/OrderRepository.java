package ru.kolpakovee.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolpakovee.restaurantapi.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
