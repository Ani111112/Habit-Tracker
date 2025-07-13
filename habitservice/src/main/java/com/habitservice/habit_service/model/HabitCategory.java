package com.habitservice.habit_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "habit_category")
public class HabitCategory extends BaseEntity {
    private String habitCatName;
    private String habitDescription;
    private Instant createdOn;
    private Instant modifiedOn;
}
