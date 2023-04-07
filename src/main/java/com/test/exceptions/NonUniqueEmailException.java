package com.test.exceptions;

public class NonUniqueEmailException extends RuntimeException {
    public NonUniqueEmailException(String message) {
        super(message);
    }
}
