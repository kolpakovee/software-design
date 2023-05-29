package ru.kolpakovee.restaurantapi.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
}
