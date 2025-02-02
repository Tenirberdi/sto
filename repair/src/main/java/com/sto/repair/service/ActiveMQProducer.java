package com.sto.repair.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.TextMessage;

@Slf4j
@Service
@AllArgsConstructor
public class ActiveMQProducer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessageWithDelay(Object message, long delayInMillis) {
        String jsonMessage;
        try {
            jsonMessage = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize message", e);
        }

        jmsTemplate.send("repair-process-queue", session -> {
            TextMessage textMessage = session.createTextMessage(jsonMessage);
            textMessage.setLongProperty("AMQ_SCHEDULED_DELAY", delayInMillis);
            return textMessage;
        });

        log.info("Message bidId {} sent with delay of {} ms", jsonMessage, delayInMillis);
    }
}
