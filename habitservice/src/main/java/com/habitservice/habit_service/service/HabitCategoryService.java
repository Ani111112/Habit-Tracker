package com.habitservice.habit_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitservice.habit_service.dto.response.UserInfoResponse;
import com.habitservice.habit_service.exception.MandatoryFieldException;
import com.habitservice.habit_service.model.Habit;
import com.habitservice.habit_service.model.HabitCategory;
import com.habitservice.habit_service.repository.HabitCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitCategoryService {
    private final HabitCategoryRepository habitCategoryRepository;
    private final ObjectMapper objectMapper;

    public void addHabit(String object, Map<String, Object> result) throws JsonProcessingException {
        HabitCategory habitCategory = objectMapper.readValue(object, HabitCategory.class);

        if(!habitCategory.haveMandatoryFilled() || !habitCategory.getHabits().stream().allMatch(Habit::haveMandatoryFilled))
            throw new MandatoryFieldException("Fill All the Mandatory Filled");

        habitCategory.setCreatedOn(new Date());
        habitCategory.getHabits().forEach(habit -> habit.setCreatedOn(new Date()));

        HabitCategory savedHabitcategory = habitCategoryRepository.save(habitCategory);

        result.put("success", savedHabitcategory);
//        UserInfoResponse userResponse = webClient.get()
//                .uri("http://localhost:8082/api/user/info/{userId}", userId)
//                .retrieve()
//                .bodyToMono(UserInfoResponse.class)
//                .block();

//        if (userResponse != null) {
//            habit.setCreatedOn(new Date());
//
//            Habit savedHabit = habitCategoryRepository.save(habit);
//            result.put("success", savedHabit);
//        } else result.put("error", "User Id Not Vaild");
    }
}
