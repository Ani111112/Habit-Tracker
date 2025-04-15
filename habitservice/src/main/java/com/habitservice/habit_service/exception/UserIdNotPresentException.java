package com.habitservice.habit_service.exception;

public class UserIdNotPresentException extends RuntimeException{
    public UserIdNotPresentException(String message) {
        super(message);
    }
}
