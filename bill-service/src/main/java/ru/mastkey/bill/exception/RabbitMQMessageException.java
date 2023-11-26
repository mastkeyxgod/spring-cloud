package ru.mastkey.bill.exception;

public class RabbitMQMessageException extends RuntimeException{
    public RabbitMQMessageException(String message) {
        super(message);
    }
}
