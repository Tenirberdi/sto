package com.sto.bid.util;

import com.sto.bid.model.entity.BidEntity;
import com.sto.bid.model.rest.Bid;
import com.sto.bid.model.rest.request.BidRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class BidMapper {
    public static BidEntity dtoToEntity(BidRequest bid) {
        return BidEntity.builder()
                .complaint(bid.getComplaint())
                .sentForRepair(false)
                .createdDt(LocalDateTime.now())
                .preferredDt(bid.getPreferredDate())
                .notes(bid.getNotes()).build();
    }

    public static Bid entityToDto(BidEntity bidEntity) {
        return Bid.builder()
                .id(bidEntity.getId())
                .customer(CustomerMapper.entityToDto(bidEntity.getCustomer()))
                .vehicle(VehicleMapper.entityToDto(bidEntity.getVehicle()))
                .complaint(bidEntity.getComplaint())
                .sentForRepair(bidEntity.getSentForRepair())
                .createdDt(bidEntity.getCreatedDt())
                .attachments(bidEntity.getAttachments().stream()
                        .map(AttachmentMapper::entityToDto)
                        .collect(Collectors.toList()))
                .preferredDt(bidEntity.getPreferredDt())
                .notes(bidEntity.getNotes()).build();
    }
}
