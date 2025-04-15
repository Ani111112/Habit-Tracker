package com.habitservice.habit_service.repository;

import com.habitservice.habit_service.model.UserHabit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHabitRepository extends JpaRepository<UserHabit, String> {
}
