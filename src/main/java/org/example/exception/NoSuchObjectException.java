package org.example.exception;

public class NoSuchObjectException extends RuntimeException {

    public NoSuchObjectException(String message) {
        super(message);
    }
}
