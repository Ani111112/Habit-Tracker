package com.habitservice.habit_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitservice.habit_service.common.CommonUtil;
import com.habitservice.habit_service.dto.response.UserInfoResponse;
import com.habitservice.habit_service.exception.HabitAlreadyPresentException;
import com.habitservice.habit_service.exception.InvalidInputException;
import com.habitservice.habit_service.model.Habit;
import com.habitservice.habit_service.model.HabitTemplate;
import com.habitservice.habit_service.repository.CollectionHandler;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final ObjectMapper objectMapper;
    private final CollectionHandler collectionHandler;
    private final CommonUtil commonUtil;
    private final WebClient.Builder webClientBuilder;

    public void addHabit(String auth, String object, Map<String, Object> result) throws JsonProcessingException {
        Habit habit = objectMapper.readValue(object, Habit.class);
        String token = commonUtil.getToken(auth);
        String emailId = commonUtil.getUserIdFromToken(token);
        UserInfoResponse userInfoResponse = webClientBuilder.build().get()
                .uri("http://User-Service/api/user/info/{emailId}", emailId)
                .retrieve()
                .bodyToMono(UserInfoResponse.class)
                .block();

        if (userInfoResponse == null) throw new RuntimeException("Email Id is not valid");

        List<String> fields = List.of("userId", "habitTempId");
        List<String> messageId = List.of(userInfoResponse.getKeyClockId(), habit.getHabitTempId());
        List<Habit> habits = collectionHandler.findDocumentsWithMultipleFieldQueries(fields, messageId, Habit.class);
        if (!habits.isEmpty()) throw new HabitAlreadyPresentException("This Habit Already Added");

        if (habit.getGoal() == null) {
            HabitTemplate habitTemplate = collectionHandler.findDocumentByField("_id", habit.getHabitTempId(), HabitTemplate.class).get(0);
            habit.setGoal(habitTemplate.getDefaultGoal());
        }

        habit.setUserId(userInfoResponse.getKeyClockId());
        habit.setCreatedAt(Instant.now());
        Habit savedHabit = (Habit) collectionHandler.save(habit);
        if(savedHabit != null) result.put("success", savedHabit);
        else result.put("error", "Unable to add the habit");
    }

    public void updateHabit(String object, Map<String, Object> result) throws JsonProcessingException {
        Habit habit = objectMapper.readValue(object, Habit.class);

        String habitId = habit.get_id();
        List<Habit> habits = collectionHandler.findDocumentByField("_id", habitId, Habit.class);
        Habit savedHabit = habits.get(0);
        if (!habit.equals(savedHabit)) {
            habit.setUpdatedAt(Instant.now());
        }

        habit.setCreatedAt(savedHabit.getCreatedAt());

        Habit updatedHabit = (Habit) collectionHandler.save(habit);
        if (updatedHabit != null) result.put("success", updatedHabit);
        else result.put("error", "Unable to update");
    }

    public void getAllActiveHabit(String auth, Map<String, Object> result) {
        String token = commonUtil.getToken(auth);
        String emailId = commonUtil.getUserIdFromToken(token);

        UserInfoResponse userInfoResponse = webClientBuilder.build().get()
                .uri("http://User-Service/api/user/info/{emailId}", emailId)
                .retrieve()
                .bodyToMono(UserInfoResponse.class)
                .block();

        if (userInfoResponse == null) throw new RuntimeException("Email Id is not valid");

        List<String> fields = List.of("userId", "activeHabit");
        List<String> messageId = List.of(userInfoResponse.getKeyClockId(), "Y");

        List<Habit> habits = collectionHandler.findDocumentsWithMultipleFieldQueries(fields, messageId, Habit.class);
        if (!habits.isEmpty()) throw new HabitAlreadyPresentException("This Habit Already Added");

        result.put("success", habits);
    }

    public void makeHabitArchived(Map<String, Object> result, String habitId) {
        if (StringUtils.isBlank(habitId)) throw new InvalidInputException("Habit id should not be blanked");

        List<Habit> habits = collectionHandler.findDocumentByField("_id", habitId, Habit.class);
        if (habits.isEmpty()) throw new InvalidInputException("Habit is not valid");

        Habit habit = habits.get(0);
        habit.setArchived(true);
        habit.setUpdatedAt(Instant.now());

        Habit savedHabit = (Habit) collectionHandler.save(habit);
        result.put("success", savedHabit);
    }
}
