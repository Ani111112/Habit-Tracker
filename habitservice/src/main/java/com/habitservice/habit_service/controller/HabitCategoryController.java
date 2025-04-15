package com.habitservice.habit_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.habitservice.habit_service.service.HabitCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/habit/v1.0/")
@RequiredArgsConstructor
public class HabitCategoryController {
    private final HabitCategoryService habitCategoryService;

    @PostMapping("/add")
    public ResponseEntity addHabit(@RequestBody String object) throws JsonProcessingException {
        Map<String, Object> result = new HashMap<>();
        habitCategoryService.addHabit(object, result);
        return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
    }
}
