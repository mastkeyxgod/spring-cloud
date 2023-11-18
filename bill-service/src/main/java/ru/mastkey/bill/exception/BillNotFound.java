package ru.mastkey.bill.exception;

public class BillNotFound extends RuntimeException{
    public BillNotFound(String message) {
        super(message);
    }
}
