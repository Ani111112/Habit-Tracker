package com.habitservice.habit_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserHabit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userHabitId;
    private long userId;
    private long habitId;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;
    private boolean wantDailyReminder = true; // By default, it is true;
    private Date createdOn;
    private Date modifiedOn;
}
