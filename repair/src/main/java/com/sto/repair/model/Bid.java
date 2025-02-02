package com.sto.repair.model;

import com.sto.repair.model.constant.RepairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    private Long id;
    private Customer customer;
    private Vehicle vehicle;
    private String complaint;
    private RepairStatus status;
    private LocalDate preferredDt;
    private LocalDate estimatedRepairDt;
    private LocalDateTime createdDt;
    private LocalDateTime repairReceivedDt;
    private LocalDateTime repairFinishedDt;
    private List<Attachment> attachments;
    private String notes;
    private Set<Issue> issues;
}
