package com.habitservice.habit_service.repository;

import com.habitservice.habit_service.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    long countByIdIn(List<Long> habitIds);
}
