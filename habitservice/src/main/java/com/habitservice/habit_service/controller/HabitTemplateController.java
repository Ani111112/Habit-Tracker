package com.habitservice.habit_service.controller;

import com.habitservice.habit_service.service.HabitTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/habit/template")
public class HabitTemplateController {
    private final HabitTemplateService habitTemplateService;

    @PostMapping("/add")
    public ResponseEntity addHabitTemplate(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitTemplateService.addHabitTemplate(result, object);
            return result.containsKey("success") ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateHabitTemplate(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            habitTemplateService.updateHabitTemplate(result, object);
            return result.containsKey("success") ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            result.put("error", ex.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
