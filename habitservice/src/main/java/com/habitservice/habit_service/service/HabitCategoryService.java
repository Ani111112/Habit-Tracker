package com.habitservice.habit_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitservice.habit_service.exception.MandatoryFieldException;
import com.habitservice.habit_service.model.Habit;
import com.habitservice.habit_service.model.HabitCategory;
import com.habitservice.habit_service.repository.CollectionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitCategoryService {
    private final CollectionHandler collectionHandler;
    private final ObjectMapper objectMapper;

    public void addHabitCategory(String object, Map<String, Object> result) throws JsonProcessingException {
        List<HabitCategory> habitCategoryList = objectMapper.readValue(object, new TypeReference<List<HabitCategory>>() {});
        habitCategoryList.forEach(habit -> {
            habit.setCreatedOn(Instant.now());
            habit.setHabitCatName(habit.getHabitCatName().toUpperCase());
            habit.setHabitDescription(habit.getHabitDescription().toUpperCase());
        });

        List<HabitCategory> savedHabitCatList = collectionHandler.saveAll(habitCategoryList);
        result.put("success", savedHabitCatList);
    }

    public void updateHabitCategory(String object, Map<String, Object> result) throws JsonProcessingException {
        HabitCategory habitCategory = objectMapper.readValue(object, HabitCategory.class);

        HabitCategory savedHabitCategory =  collectionHandler.findDocumentByField("_id", habitCategory.get_id(), HabitCategory.class).get(0);

        if (!savedHabitCategory.getHabitDescription().equals(habitCategory.getHabitDescription().toUpperCase())) {
            habitCategory.setCreatedOn(savedHabitCategory.getCreatedOn());
            habitCategory.setModifiedOn(Instant.now());
            HabitCategory updatedHabitCat = (HabitCategory) collectionHandler.save(habitCategory);
            result.put("success", updatedHabitCat);
        } else result.put("error", "No Changes Happen");
    }
}
