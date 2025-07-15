package com.habitservice.habit_service.common;

import com.habitservice.habit_service.dto.request.KafkaDTO;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, KafkaDTO> kafkaTemplate;

    public void sendMessage(String topic, KafkaDTO message) {
        kafkaTemplate.send(topic, message);
    }
}
