package org.example.exception;

public class IncorrectGender extends RuntimeException {
    public IncorrectGender(String message) {
        super(message);
    }
}