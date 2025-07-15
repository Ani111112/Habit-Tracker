package com.habitservice.habit_service.dto.request;

import lombok.*;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaDTO {
    private String to;
    private String subject;
    private String templateName;
    private String habitName;
    private String userName;
}
