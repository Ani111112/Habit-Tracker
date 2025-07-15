package com.schedulerservice.scheduler_service.consumer;

import com.schedulerservice.scheduler_service.dto.KafkaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailTemplate {
    private final CustomMailSender mailSender;
    public void getTemplate(String templateName, KafkaDTO kafkaDTO, String toMail, String fromMail) {
        try {
            String templateContent = loadTemplate(templateName);
            log.info("Template content : {}", templateContent);
            Map<String, String> placeholders = Map.of("habitName", kafkaDTO.getHabitName(),
                    "username", kafkaDTO.getUserName());
            String template = processTemplate(templateContent, placeholders);
            log.info("Process Template is : {}", template);
            mailSender.sendEmail(template, toMail, fromMail, kafkaDTO.getSubject());
        } catch (Exception ex) {
            log.error("Exception Occurred : {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }

    }

    private String loadTemplate(String templateName) throws Exception {
        ClassPathResource resource = new ClassPathResource("templates/" + templateName);
        return Files.readString(resource.getFile().toPath(), StandardCharsets.UTF_8);
    }

    private String processTemplate(String template, Map<String, String> placeholders) {
        String content = template;
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return content;
    }
}
