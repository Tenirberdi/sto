package com.sto.bid.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    private Long id;
    private Customer customer;
    private Vehicle vehicle;
    private String complaint;
    private Boolean sentForRepair;
    private LocalDate preferredDt;
    private LocalDateTime createdDt;
    private List<Attachment> attachments;
    private String notes;
}
