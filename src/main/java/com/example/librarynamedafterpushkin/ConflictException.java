package com.example.librarynamedafterpushkin;

public class ConflictException extends RuntimeException {
    ConflictException(String message) {
        super(message);
    }
}