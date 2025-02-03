package com.sto.check.util;

import com.sto.check.model.Bid;
import com.sto.check.model.entity.BidEntity;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class BidMapper {
    public static BidEntity dtoToEntity(Bid bid) {
        return BidEntity.builder()
                .id(bid.getId())
                .status(bid.getStatus())
                .customer(CustomerMapper.dtoToEntity(bid.getCustomer()))
                .vehicle(VehicleMapper.dtoToEntity(bid.getVehicle()))
                .complaint(bid.getComplaint())
                .createdDt(LocalDateTime.now())
                .preferredDt(bid.getPreferredDt())
                .estimatedRepairDt(bid.getEstimatedRepairDt())
                .repairReceivedDt(bid.getRepairReceivedDt())
                .repairFinishedDt(bid.getRepairFinishedDt())
                .issueCodes(bid.getIssues()
                        .stream()
                        .map(IssueCodeMapper::dtoToEntity)
                        .collect(Collectors.toSet()))
                .totalPrice(bid.getTotalPrice())
                .notes(bid.getNotes()).build();
    }

    public static Bid entityToDto(BidEntity bidEntity) {
        return Bid.builder()
                .id(bidEntity.getId())
                .status(bidEntity.getStatus())
                .customer(CustomerMapper.entityToDto(bidEntity.getCustomer()))
                .vehicle(VehicleMapper.entityToDto(bidEntity.getVehicle()))
                .complaint(bidEntity.getComplaint())
                .createdDt(bidEntity.getCreatedDt())
                .attachments(bidEntity.getAttachments()
                        .stream()
                        .map(AttachmentMapper::entityToDto)
                        .collect(Collectors.toList()))
                .preferredDt(bidEntity.getPreferredDt())
                .repairReceivedDt(bidEntity.getRepairReceivedDt())
                .issues(bidEntity.getIssueCodes().stream()
                        .map(IssueCodeMapper::entityToDto)
                        .collect(Collectors.toSet()))
                .repairFinishedDt(bidEntity.getRepairFinishedDt())
                .totalPrice(bidEntity.getTotalPrice())
                .notes(bidEntity.getNotes()).build();
    }
}
