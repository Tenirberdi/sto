package com.sto.repair.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sto.repair.model.Bid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.sto.repair.model.constant.Constant.MDC_REQUEST_ID;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {
    private final ObjectMapper objectMapper;
    private final RepairService repairService;

    @KafkaListener(topics = "${kafka.repair.topic}", groupId = "my-group")
    public void listen(String message) {
        try {
            MDC.put(MDC_REQUEST_ID, UUID.randomUUID().toString());
            log.info("Received message: {}", message);
            Bid bid = objectMapper.readValue(message, Bid.class);
            repairService.startOfRepair(bid);
        } catch (JsonProcessingException e) {
            log.error("Could not deserialize message");
            log.error(e.getMessage(), e);
        } finally {
            MDC.clear();
        }
    }
}
