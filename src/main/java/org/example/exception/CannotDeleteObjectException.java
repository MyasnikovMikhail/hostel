package org.example.exception;

public class CannotDeleteObjectException extends RuntimeException {

    public CannotDeleteObjectException(String message) {
        super(message);
    }
}
