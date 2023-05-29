package ru.kolpakovee.restaurantapi.pojo;

import lombok.Data;
import ru.kolpakovee.restaurantapi.entity.Order;
import ru.kolpakovee.restaurantapi.enums.OrderStatus;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private OrderStatus status;
    private String specialRequests;
    private LocalDateTime updatedAt;
    private String userEmail;

    public OrderResponse(Order order){
        id = order.getId();
        status = order.getStatus();
        specialRequests = order.getSpecialRequests();
        updatedAt = order.getUpdatedAt();
        userEmail = order.getUser().getEmail();
    }
}
