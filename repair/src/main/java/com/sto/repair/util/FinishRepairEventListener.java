package com.sto.repair.util;

import com.sto.repair.model.event.FinishRepairEvent;
import com.sto.repair.service.RepairService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.sto.repair.model.constant.Constant.MDC_REQUEST_ID;

@Slf4j
@Component
@AllArgsConstructor
public class FinishRepairEventListener implements ApplicationListener<FinishRepairEvent> {
    private final RepairService repairService;
    @Override
    public void onApplicationEvent(FinishRepairEvent event) {
        try {
            MDC.put(MDC_REQUEST_ID, UUID.randomUUID().toString());
            log.info("Received finish repair event for bidId {} ", event.getBidId());
            repairService.endOfRepair(event.getBidId());
        } finally {
            MDC.clear();
        }
    }
}
