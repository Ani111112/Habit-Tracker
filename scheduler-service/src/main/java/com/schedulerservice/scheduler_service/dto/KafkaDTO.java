package com.schedulerservice.scheduler_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaDTO {
    private String to;
    private String subject;
    private String templateName;
    private String habitName;
    private String userName;
}
