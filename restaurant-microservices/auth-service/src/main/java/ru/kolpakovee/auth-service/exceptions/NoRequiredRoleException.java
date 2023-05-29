package ru.kolpakovee.authorization_microservice.exceptions;

public class NoRequiredRoleException extends RuntimeException {
    public NoRequiredRoleException(String message) {
        super(message);
    }
}
