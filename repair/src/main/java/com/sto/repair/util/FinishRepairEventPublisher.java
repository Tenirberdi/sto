package com.sto.repair.util;

import com.sto.repair.model.event.FinishRepairEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class FinishRepairEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Long message) {
        log.info("Publishing repair finish event for bidId {}", message);
        FinishRepairEvent customSpringEvent = new FinishRepairEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
