package com.habitservice.habit_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitservice.habit_service.dto.response.UserInfoResponse;
import com.habitservice.habit_service.exception.UserIdNotPresentException;
import com.habitservice.habit_service.model.UserHabit;
import com.habitservice.habit_service.repository.HabitRepository;
import com.habitservice.habit_service.repository.UserHabitRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserHabitService {
    private final UserHabitRepository userHabitRepository;
    private final ObjectMapper objectMapper;
    private final WebClient.Builder webClientBuilder;
    private final HabitRepository habitRepository;


    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public void addUserHabit(String object, Map<String, Object> result) throws JsonProcessingException {
        List<UserHabit> userHabit = objectMapper.readValue(object, new TypeReference<>() {});
        String userId = userHabit.get(0).getUserEmailId();
        List<Long> habitId = userHabit.stream().map(UserHabit::getHabitId).toList();

        boolean isAllHabitIdValid = habitRepository.countByIdIn(habitId) == habitId.size();
        if (!isAllHabitIdValid) throw new IllegalArgumentException("Invalid Habit Id....");

        UserInfoResponse userResponse = webClientBuilder.build().get()
                .uri("http://User-Service/api/user/info/{emailId}", userId)
                .retrieve()
                .bodyToMono(UserInfoResponse.class)
                .block();

        if (userResponse != null && !userResponse.isHaveNullFilled()) {
            userHabit.forEach(user -> user.setCreatedOn(new Date()));

            List<UserHabit> savedUserHabit = userHabitRepository.saveAll(userHabit);
            result.put("success", savedUserHabit);
        } else result.put("error", String.format("Unable to add Habit in %s id", userId));
    }

    public void fallbackMethod(String object, Map<String, Object> result, Throwable throwable) {
        throw new RuntimeException("Opps Something went wrong");
    }
}