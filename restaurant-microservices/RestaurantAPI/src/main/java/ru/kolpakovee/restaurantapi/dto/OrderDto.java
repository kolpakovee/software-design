package ru.kolpakovee.restaurantapi.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String specialRequests;
    private Long userId;
}
