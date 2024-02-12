package org.example.exception;

public class NoAvailableSeats extends RuntimeException {

    public NoAvailableSeats(String message) {
        super(message);
    }
}
