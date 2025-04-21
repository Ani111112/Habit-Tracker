package com.userservice.User_Service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.User_Service.Mappers.UserInfoMapper;
import com.userservice.User_Service.Mappers.UserResponseMapper;
import com.userservice.User_Service.dto.request.UserRequest;
import com.userservice.User_Service.dto.response.UserInfoResponse;
import com.userservice.User_Service.dto.response.UserResponse;
import com.userservice.User_Service.exception.MandatoryFieldException;
import com.userservice.User_Service.exception.UserNotPresentException;
import com.userservice.User_Service.model.ApplicationUser;
import com.userservice.User_Service.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    public UserResponse signUp(UserRequest object) throws JsonProcessingException {
        ApplicationUser applicationUser = UserInfoMapper.INSTANCE.map(object);
        if (!applicationUser.mandatoryFilledCheck()) throw new MandatoryFieldException("Fill all the mandatory field");

        applicationUser.setCreatedOn(new Date());
        Optional<ApplicationUser> optionalSavedUser = userRepository.findByEmailId(applicationUser.getEmailId());

        if (optionalSavedUser.isPresent()) {
            ApplicationUser savedUser = optionalSavedUser.get();
            if (StringUtils.isNotBlank(savedUser.getKeyClockId())) return UserResponseMapper.INSTANCE.map(savedUser);
            savedUser.setModifiedOn(new Date());
            savedUser.setKeyClockId(applicationUser.getKeyClockId());
            return UserResponseMapper.INSTANCE.map(userRepository.save(savedUser));
        } else {
            ApplicationUser savedUser = userRepository.save(applicationUser);
            return UserResponseMapper.INSTANCE.map(userRepository.save(savedUser));
        }
    }

    public UserInfoResponse getUserInfo(String emailId) {
        if (ObjectUtils.isEmpty(emailId)) throw new MandatoryFieldException("User id should not be empty");

        ApplicationUser applicationUser = userRepository.findByEmailId(emailId).orElseThrow(() -> new UserNotPresentException("User Not Exits"));
        return UserInfoMapper.INSTANCE.map(applicationUser);
    }


    public UserResponse updateUser(UserRequest object) {
        ApplicationUser applicationUser = UserInfoMapper.INSTANCE.map(object);

        ApplicationUser alreadySavedApplication = userRepository.findByEmailId(applicationUser.getEmailId()).orElseThrow(() -> new UserNotPresentException("Email id not exits"));
        if (!alreadySavedApplication.equals(applicationUser)) {
            applicationUser.setCreatedOn(alreadySavedApplication.getCreatedOn());
            applicationUser.setModifiedOn(new Date());
            return UserResponseMapper.INSTANCE.map(userRepository.save(applicationUser));
        }
        return null;
    }
}
