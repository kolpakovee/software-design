package ru.kolpakovee.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolpakovee.restaurantapi.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
