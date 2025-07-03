package com.habitservice.habit_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserHabit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userHabitId;
    private String userEmailId;
    private long habitId;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;
    private boolean wantDailyReminder = true; // By default, it is true;
    private boolean activeHabit = true; // By default All Habit is Active
    private Date createdOn;
    private Date modifiedOn;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserHabit userHabit = (UserHabit) object;
        return habitId == userHabit.habitId && wantDailyReminder == userHabit.wantDailyReminder && activeHabit == userHabit.activeHabit && Objects.equals(startDate, userHabit.startDate) && Objects.equals(endDate, userHabit.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitId, startDate, endDate, wantDailyReminder, activeHabit);
    }
}
