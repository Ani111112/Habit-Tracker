package com.habitservice.habit_service.controller;

import com.habitservice.habit_service.service.UserHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/habit")
@RequiredArgsConstructor
public class UserHabitController {
    private final UserHabitService userHabitService;

    @PostMapping("/add")
    public ResponseEntity addUserHabit(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            userHabitService.addUserHabit(object, result);
            return result.containsKey("success") ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
