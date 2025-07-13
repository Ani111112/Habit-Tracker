package com.habitservice.habit_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitservice.habit_service.model.HabitTemplate;
import com.habitservice.habit_service.repository.CollectionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HabitTemplateService {
    private final CollectionHandler collectionHandler;
    private final ObjectMapper objectMapper;

    public void addHabitTemplate(Map<String, Object> result, String object) throws JsonProcessingException {
        List<HabitTemplate> habitTemplateList = objectMapper.readValue(object, new TypeReference<List<HabitTemplate>>() {});

        habitTemplateList.forEach(habitTemp -> {
            habitTemp.setCreatedAt(Instant.now());
        });

        List<HabitTemplate> savedHabitTemplate = collectionHandler.saveAll(habitTemplateList);
        result.put("success", savedHabitTemplate);
    }

    public void updateHabitTemplate(Map<String, Object> result, String object) throws JsonProcessingException {
        HabitTemplate habitTemplate = objectMapper.readValue(object, HabitTemplate.class);

        HabitTemplate savedHabitTemplate = collectionHandler.findDocumentByField("_id", habitTemplate.get_id(), HabitTemplate.class).get(0);

        if (!habitTemplate.equals(savedHabitTemplate)) {
            habitTemplate.setModifiedAt(Instant.now());
            habitTemplate.setCreatedAt(savedHabitTemplate.getCreatedAt());
            HabitTemplate updatedHabitTemplate = (HabitTemplate) collectionHandler.save(habitTemplate);
            result.put("success", updatedHabitTemplate);
        } else result.put("error", "No changes happens");
    }
}
