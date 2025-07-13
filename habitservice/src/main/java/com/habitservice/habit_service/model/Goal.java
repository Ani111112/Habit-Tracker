package com.habitservice.habit_service.model;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Data
public class Goal {
    private int frequency;             // e.g., 3 times
    private String unit;               // DAY, WEEK, MONTH
    private List<Integer> repeatDays;  // e.g., [1, 3, 5] for Mon/Wed/Fri (optional)

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Goal goal = (Goal) object;
        return frequency == goal.frequency && Objects.equals(unit, goal.unit) && new HashSet<>(repeatDays).containsAll(goal.repeatDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, unit, repeatDays);
    }
}
