package ru.kolpakovee.restaurantapi.factories;

import ru.kolpakovee.restaurantapi.dto.DishDto;
import ru.kolpakovee.restaurantapi.entity.Dish;

public class DishFactory {
    public static Dish makeDish(DishDto dishDto){
        Dish dish = new Dish();

        dish.setName(dishDto.getName());
        dish.setPrice(dishDto.getPrice());
        dish.setQuantity(dishDto.getQuantity());
        dish.setAvailable(dishDto.isAvailable());
        dish.setDescription(dishDto.getDescription());

        return dish;
    }
}
