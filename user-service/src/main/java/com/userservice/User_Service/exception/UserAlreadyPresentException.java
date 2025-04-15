package com.userservice.User_Service.exception;

public class UserAlreadyPresentException extends RuntimeException{
    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
