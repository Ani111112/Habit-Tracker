package com.habitservice.habit_service.controller;

import com.habitservice.habit_service.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/habit")
public class HabitController {
    private final HabitService habitService;

    @PostMapping("/add")
    public ResponseEntity addHabit(@RequestHeader("Authorization") String auth, @RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitService.addHabit(auth, object, result);
            return result.containsKey("success") ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateHabit(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitService.updateHabit(object, result);
            return result.containsKey("success") ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllActiveHabit(@RequestHeader("Authorization") String auth) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitService.getAllActiveHabit(auth, result);
            return result.containsKey("success") ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/archived/{habitId}")
    public ResponseEntity makeHabitArchived(@PathVariable String habitId) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitService.makeHabitArchived(result, habitId);
            return result.containsKey("success") ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
