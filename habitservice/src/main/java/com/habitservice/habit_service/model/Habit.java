package com.habitservice.habit_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.habitservice.habit_service.Enum.HabitFrequency;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String habitName;
    private String habitDescription;
    private Date createdOn;
    private Date modifiedOn;

    @ManyToOne
    @JoinColumn(name = "habit_cat_id", referencedColumnName = "id", updatable = true, insertable = true)
    @JsonBackReference
    private HabitCategory habitCategory;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Habit habit = (Habit) object;
        return Objects.equals(habitName, habit.habitName) && Objects.equals(habitDescription, habit.habitDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitName, habitDescription);
    }

    public boolean haveMandatoryFilled() {
        return StringUtils.isNotBlank(this.habitName) && StringUtils.isNotBlank(this.habitDescription);
    }
}
