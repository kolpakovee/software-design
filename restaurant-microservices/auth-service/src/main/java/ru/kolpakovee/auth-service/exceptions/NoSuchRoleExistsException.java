package ru.kolpakovee.authorization_microservice.exceptions;

public class NoSuchRoleExistsException extends RuntimeException{
    public NoSuchRoleExistsException(String message){
        super(message);
    }
}
