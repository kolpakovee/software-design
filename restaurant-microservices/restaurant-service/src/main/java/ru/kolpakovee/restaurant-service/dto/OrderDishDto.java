package ru.kolpakovee.restaurantapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderDishDto {
    @Min(value = 0, message = "Dish id cannot be less than zero.")
    private Long dishId;
    @Min(value = 0, message = "Quantity cannot be less than zero.")
    private int quantity;
    @DecimalMin(value = "0", message = "Price cannot be less than zero.")
    private BigDecimal price;
}
