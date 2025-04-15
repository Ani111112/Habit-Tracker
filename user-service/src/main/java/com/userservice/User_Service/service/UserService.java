package com.userservice.User_Service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.password4j.Password;
import com.userservice.User_Service.Mappers.UserInfoMapper;
import com.userservice.User_Service.dto.response.UserInfoResponse;
import com.userservice.User_Service.exception.MandatoryFieldException;
import com.userservice.User_Service.exception.UserAlreadyPresentException;
import com.userservice.User_Service.exception.UserNotPresentException;
import com.userservice.User_Service.model.ApplicationUser;
import com.userservice.User_Service.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    public void signUp(Map<String, Object> result, String object) throws JsonProcessingException {
        ApplicationUser applicationUser = objectMapper.readValue(object, ApplicationUser.class);
        if (!applicationUser.mandatoryFilledCheck()) throw new MandatoryFieldException("Fill all the mandatory field");

        userRepository.findByEmailId(applicationUser.getEmailId()).ifPresent(user -> {
            log.error("{} Already Present in Database", applicationUser.getEmailId());
            throw new UserAlreadyPresentException("Email id already registered....Try to login");
        });

        applicationUser.setPassword((Password.hash(applicationUser.getPassword()).addRandomSalt().withArgon2()).getResult());
        applicationUser.setCreatedOn(new Date());
        ApplicationUser savedApplicationUser = userRepository.save(applicationUser);
        result.put("success", savedApplicationUser);
    }

    public UserInfoResponse getUserInfo(Long userId) {
        if (ObjectUtils.isEmpty(userId)) throw new MandatoryFieldException("User id should not be empty");

        ApplicationUser applicationUser = userRepository.findById(userId).orElseThrow(() -> new UserNotPresentException("User Not Exits"));
        return UserInfoMapper.INSTANCE.map(applicationUser);
    }


    public void updateUser(Map<String, Object> result, String object) throws JsonProcessingException {
        ApplicationUser applicationUser = objectMapper.readValue(object, ApplicationUser.class);

        ApplicationUser alreadySavedApplication = userRepository.findById(applicationUser.getUserId()).orElseThrow(() -> new UserNotPresentException("Email id not exits"));

        if (!alreadySavedApplication.equals(applicationUser)) {
            applicationUser.setCreatedOn(alreadySavedApplication.getCreatedOn());
            applicationUser.setModifiedOn(new Date());
            ApplicationUser updatedUser = userRepository.save(applicationUser);
            result.put("success", updatedUser);
        } else result.put("success", "User Profile Update Successfully");
    }
}
