package com.flab.demo.exception;

public class NotAuthenticationException extends RuntimeException {
    public NotAuthenticationException(String message) {
        super(message);
    }
}
