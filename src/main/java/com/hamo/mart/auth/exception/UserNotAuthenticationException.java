package com.hamo.mart.auth.exception;

public class UserNotAuthenticationException extends  RuntimeException {
    public UserNotAuthenticationException() {
        super("User is not authenticated.");
    }
}
