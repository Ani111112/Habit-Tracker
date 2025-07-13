package com.habitservice.habit_service.exception;

public class HabitAlreadyPresentException extends RuntimeException{
    public HabitAlreadyPresentException(String message) {
        super(message);
    }
}
