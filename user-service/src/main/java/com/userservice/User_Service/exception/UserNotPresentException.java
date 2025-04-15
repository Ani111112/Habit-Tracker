package com.userservice.User_Service.exception;

public class UserNotPresentException extends RuntimeException {
    public UserNotPresentException(String message) {
        super(message);
    }
}
