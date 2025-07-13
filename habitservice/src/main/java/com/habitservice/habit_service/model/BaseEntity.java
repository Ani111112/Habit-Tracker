package com.habitservice.habit_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseEntity {
    @MongoId
    private String _id = new ObjectId().toString();
}
