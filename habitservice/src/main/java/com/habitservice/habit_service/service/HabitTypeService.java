//package com.habitservice.habit_service.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.habitservice.habit_service.exception.MandatoryFieldException;
//import com.habitservice.habit_service.model.HabitCategory;
////import com.habitservice.habit_service.model.HabitTypes;
//import com.habitservice.habit_service.repository.HabitTypeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class HabitTypeService {
//    private final HabitTypeRepository habitTypeRepository;
//    private final ObjectMapper objectMapper;
//
//    public void addHabitType(String object, Map<String, Object> result) throws JsonProcessingException {
//        HabitTypes habitTypes = objectMapper.readValue(object, HabitTypes.class);
//
//        if (!habitTypes.haveMandatoryFilled() || !habitTypes.getHabitCategory().stream().allMatch(HabitCategory::haveMandatoryFilled))
//            throw new MandatoryFieldException("Fill all the mandatory filed");
//
//        habitTypes.setHabitName(habitTypes.getHabitName().toUpperCase());
//        habitTypes.setHabitDescription(habitTypes.getHabitDescription().toUpperCase());
//        habitTypes.setCreatedOn(new Date());
//
//        habitTypes.getHabitCategory().forEach(cat -> {
//            cat.setName(cat.getName().toUpperCase());
//            cat.setCreatedOn(new Date());
//        });
//
//        HabitTypes savedHabitTypes = habitTypeRepository.save(habitTypes);
//        result.put("success", savedHabitTypes);
//    }
//}
