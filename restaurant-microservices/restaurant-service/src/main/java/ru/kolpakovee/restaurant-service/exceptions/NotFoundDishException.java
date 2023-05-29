package ru.kolpakovee.restaurantapi.exceptions;

public class NotFoundDishException extends RuntimeException {
    public NotFoundDishException(String message) {
        super(message);
    }
}
