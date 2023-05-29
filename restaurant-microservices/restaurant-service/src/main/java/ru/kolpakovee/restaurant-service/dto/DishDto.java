package ru.kolpakovee.restaurantapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishDto {
    @NotBlank(message = "Dish name cannot be blank.")
    private String name;
    @NotBlank(message = "Dish description cannot be blank.")
    private String description;
    @DecimalMin(value = "0", message = "Price cannot be less than zero.")
    private BigDecimal price;
    @Min(value = 0, message = "Quantity cannot be less than zero.")
    private int quantity;
    private boolean isAvailable = true;
}
