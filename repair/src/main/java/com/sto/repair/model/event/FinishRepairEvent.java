package com.sto.repair.model.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

@Getter
public class FinishRepairEvent extends ApplicationEvent {
    private Long bidId;

    public FinishRepairEvent(Object source, Long bidId) {
        super(source);
        this.bidId = bidId;
    }
}
