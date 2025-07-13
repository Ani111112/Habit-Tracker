package com.userservice.User_Service.controller;

import com.userservice.User_Service.dto.request.UserRequest;
import com.userservice.User_Service.dto.response.UserInfoResponse;
import com.userservice.User_Service.dto.response.UserResponse;
import com.userservice.User_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
}
