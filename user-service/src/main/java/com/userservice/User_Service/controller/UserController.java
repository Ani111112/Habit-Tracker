package com.userservice.User_Service.controller;

import com.userservice.User_Service.dto.request.UserRequest;
import com.userservice.User_Service.dto.response.UserInfoResponse;
import com.userservice.User_Service.dto.response.UserResponse;
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
    public UserResponse signUp(@RequestBody UserRequest request) {
        try {
            return userService.signUp(request);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/info/{emailId}")
    public UserInfoResponse getUserInfo(@PathVariable String emailId) {
        try {
            return userService.getUserInfo(emailId);
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
