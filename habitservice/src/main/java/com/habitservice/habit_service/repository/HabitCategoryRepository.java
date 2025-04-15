package com.habitservice.habit_service.repository;

import com.habitservice.habit_service.model.Habit;
import com.habitservice.habit_service.model.HabitCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitCategoryRepository extends JpaRepository<HabitCategory, Long> {
}
