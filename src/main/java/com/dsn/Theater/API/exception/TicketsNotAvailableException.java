package com.dsn.Theater.API.exception;

public class TicketsNotAvailableException extends RuntimeException {
    public TicketsNotAvailableException(String message) {
        super(message);
    }
}
