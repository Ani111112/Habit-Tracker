package com.habitservice.habit_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.habitservice.habit_service.Enum.HabitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "habit")
public class Habit extends BaseEntity{
    private String userId; // Keycloak sub
    private String habitTempId; // habit template id
    private String description;
    private HabitType type;
    private Goal goal;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;
    private String activeHabit = "Y";
    private boolean reminderEnabled;
    private boolean archived = false; //default is not archived
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Habit habit = (Habit) object;
        return reminderEnabled == habit.reminderEnabled && archived == habit.archived && Objects.equals(description, habit.description) && type == habit.type && Objects.equals(goal, habit.goal) && Objects.equals(startDate, habit.startDate) && Objects.equals(activeHabit, habit.activeHabit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, goal, startDate, activeHabit, reminderEnabled, archived);
    }
}
