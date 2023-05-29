package ru.kolpakovee.restaurantapi.exceptions;

public class NoRequiredRoleException extends RuntimeException {
    public NoRequiredRoleException(String message) {
        super(message);
    }
}
