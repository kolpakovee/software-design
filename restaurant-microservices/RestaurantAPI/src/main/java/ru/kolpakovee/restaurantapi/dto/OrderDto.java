package ru.kolpakovee.restaurantapi.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String specialRequests;
    @Size(min = 1, message = "Order must contain at least one dish.")
    private List<OrderDishDto> orderDishList;
}
