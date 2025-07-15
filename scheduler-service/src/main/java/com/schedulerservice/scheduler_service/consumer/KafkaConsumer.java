package com.schedulerservice.scheduler_service.consumer;

import com.schedulerservice.scheduler_service.dto.KafkaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final EmailTemplate template;
    @Value("${spring.mail.from}")
    private String from;

    @KafkaListener(topics = "habit-topic", groupId = "habit-consumer-group")
    public void listen(KafkaDTO kafkaDTO) {
        log.info("Kafka Listen Successfully for habit name is {}", kafkaDTO.getHabitName());
        template.getTemplate(kafkaDTO.getTemplateName(), kafkaDTO, kafkaDTO.getTo(), from);
    }
}
