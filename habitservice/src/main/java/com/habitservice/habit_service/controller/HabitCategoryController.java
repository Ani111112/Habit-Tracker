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
@RequestMapping("/api/habit-cat/v1.0/")
@RequiredArgsConstructor
public class HabitCategoryController {
    private final HabitCategoryService habitCategoryService;

    @PostMapping("/add")
    public ResponseEntity addHabitCategory(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitCategoryService.addHabitCategory(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateHabitCategory(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitCategoryService.updateHabitCategory(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
