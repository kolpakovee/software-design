package ru.kolpakovee.restaurantapi.exceptions;

public class NotFoundEmailException extends RuntimeException{
    public NotFoundEmailException(String message){
        super(message);
    }
}
