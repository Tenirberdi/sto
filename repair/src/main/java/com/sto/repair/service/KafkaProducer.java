package com.sto.repair.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.check.topic}")
    private String checkTopic;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Object message) {
        String value;
        try {
            value = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("Could not send message");
            log.error(e.getMessage(), e);
            return;
        }
        kafkaTemplate.send(checkTopic, value);
        log.info("BidId {} sent to form check", message);
    }
}