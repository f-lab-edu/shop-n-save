package com.flab.demo.exception;

public class UserAuthenticationFailException extends RuntimeException{

    public UserAuthenticationFailException(String message) {
        super(message);
    }
}
