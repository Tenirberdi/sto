package com.sto.repair.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sto.repair.util.WorkImitationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.sto.repair.model.constant.Constant.MDC_REQUEST_ID;

@Slf4j
@Service
@AllArgsConstructor
public class ActiveMQConsumer {
    private final WorkImitationUtil workImitationUtil;
    private final ObjectMapper objectMapper;

    @JmsListener(destination = "repair-process-queue")
    public void receiveMessage(String message) {
        try {
            log.info("Received message: " + message);
            Long bidId;
            try {
                bidId = objectMapper.readValue(message, Long.class);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                return;
            }
            MDC.put(MDC_REQUEST_ID, UUID.randomUUID().toString());
            workImitationUtil.endWorkImitation(bidId);
        } finally {
            MDC.clear();
        }
    }
}
