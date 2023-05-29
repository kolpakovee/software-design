package ru.kolpakovee.restaurantapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolpakovee.restaurantapi.dto.DishDto;
import ru.kolpakovee.restaurantapi.entity.Dish;
import ru.kolpakovee.restaurantapi.enums.OrderStatus;
import ru.kolpakovee.restaurantapi.enums.UserRole;
import ru.kolpakovee.restaurantapi.exceptions.NoRequiredRoleException;
import ru.kolpakovee.restaurantapi.exceptions.NotFoundDishException;
import ru.kolpakovee.restaurantapi.exceptions.OrderNotFoundException;
import ru.kolpakovee.restaurantapi.repositories.DishRepository;
import ru.kolpakovee.restaurantapi.repositories.OrderDishRepository;
import ru.kolpakovee.restaurantapi.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static ru.kolpakovee.restaurantapi.factories.DishFactory.makeDish;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final OrderDishRepository orderDishRepository;
    private final OrderRepository orderRepository;

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }


    public Dish getDishById(Long id, String role) {
        hasManagerRole(role);

        var dish = dishRepository.findById(id);

        if (dish.isEmpty()) throw new NotFoundDishException("Dish not found");

        return dish.get();
    }

    public Dish createDish(DishDto dishDto, String role) {
        hasManagerRole(role);

        Dish dish = makeDish(dishDto);

        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, DishDto dishDto, String role) {
        hasManagerRole(role);

        var dish = dishRepository.findById(id);

        if (dish.isEmpty()) throw new NotFoundDishException("Dish not found");

        dish.get().setName(dishDto.getName());
        dish.get().setPrice(dishDto.getPrice());
        dish.get().setQuantity(dishDto.getQuantity());
        dish.get().setAvailable(dishDto.isAvailable());
        dish.get().setDescription(dishDto.getDescription());
        dish.get().setUpdatedAt(LocalDateTime.now());

        return dishRepository.save(dish.get());
    }

    public void deleteDish(Long id, String role) {
        hasManagerRole(role);

        var dish = dishRepository.findById(id);

        if (dish.isEmpty()) throw new NotFoundDishException("Dish not found");

        var orderDishes = orderDishRepository.findAllByDish(dish.get());

        for(var orderDish : orderDishes){
            orderDishRepository.delete(orderDish);
            var order = orderRepository.findById(orderDish.getOrder().getId());
            if (order.isEmpty()) throw new OrderNotFoundException("Order not found");
            order.get().setStatus(OrderStatus.CANCELED);
            order.get().setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order.get());
        }

        dishRepository.delete(dish.get());
    }

    private void hasManagerRole(String role) {
        if (!Objects.equals(role, UserRole.MANAGER.name())) {
            throw new NoRequiredRoleException("To view the list of dishes you needed a role " + UserRole.MANAGER);
        }
    }
}
