package com.userservice.User_Service.controller;

import com.userservice.User_Service.dto.response.UserInfoResponse;
import com.userservice.User_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.signUp(result, object);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info/{userId}")
    public UserInfoResponse getUserInfo(@PathVariable Long userId) {
        try {
            return userService.getUserInfo(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.updateUser(result, object);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
