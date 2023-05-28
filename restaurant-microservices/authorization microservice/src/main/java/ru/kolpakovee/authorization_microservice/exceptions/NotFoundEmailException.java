package ru.kolpakovee.authorization_microservice.exceptions;

public class NotFoundEmailException extends RuntimeException{
    public NotFoundEmailException(String message){
        super(message);
    }
}
