package ru.mastkey.account.exception;

public class RabbitMQMessageException extends RuntimeException{
    public RabbitMQMessageException(String message) {
        super(message);
    }
}
