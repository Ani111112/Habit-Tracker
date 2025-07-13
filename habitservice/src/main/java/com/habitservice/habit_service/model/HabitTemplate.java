package com.habitservice.habit_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "habit_template")
public class HabitTemplate extends BaseEntity{
    private String habitName;
    private String habitCatId;
    private String emoji;
    private String colorCode;
    private List<String> tags;
    private Goal defaultGoal;
    private Instant createdAt;
    private Instant modifiedAt;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HabitTemplate that = (HabitTemplate) object;
        return Objects.equals(emoji, that.emoji) && Objects.equals(colorCode, that.colorCode) && new HashSet<>(tags).containsAll(that.tags) && defaultGoal.equals(that.defaultGoal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emoji, colorCode, tags, defaultGoal);
    }
}
