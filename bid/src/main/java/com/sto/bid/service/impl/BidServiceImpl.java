package com.sto.bid.service.impl;

import com.sto.bid.exception.EntityNotFoundException;
import com.sto.bid.model.entity.AttachmentEntity;
import com.sto.bid.model.entity.BidEntity;
import com.sto.bid.model.entity.CustomerEntity;
import com.sto.bid.model.entity.VehicleEntity;
import com.sto.bid.model.rest.Bid;
import com.sto.bid.model.rest.request.AttachmentRequest;
import com.sto.bid.model.rest.request.BidRequest;
import com.sto.bid.repository.AttachmentRepository;
import com.sto.bid.repository.BidRepository;
import com.sto.bid.repository.CustomerRepository;
import com.sto.bid.repository.VehicleRepository;
import com.sto.bid.service.BidService;
import com.sto.bid.service.KafkaProducer;
import com.sto.bid.util.AttachmentMapper;
import com.sto.bid.util.BidMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BidServiceImpl implements BidService {
    private final KafkaProducer kafkaProducer;
    private final BidRepository bidRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Long save(BidRequest bid) {
        BidEntity bidEntity = BidMapper.dtoToEntity(bid);
        CustomerEntity customer = customerRepository.findById(bid.getCustomerId()).orElseThrow(EntityNotFoundException::new);
        bidEntity.setCustomer(customer);
        VehicleEntity vehicle = vehicleRepository.findById(bid.getVehicleId()).orElseThrow(EntityNotFoundException::new);
        bidEntity.setVehicle(vehicle);
        bidRepository.save(bidEntity);
        for (AttachmentRequest attachment : bid.getAttachments()) {
            AttachmentEntity attachmentEntity = AttachmentMapper.dtoToEntity(attachment);
            attachmentEntity.setBid(bidEntity);
            attachmentRepository.save(attachmentEntity);
        }
        return bidEntity.getId();
    }

    @Override
    public Bid get(Long bidId) {
        BidEntity bidEntity = bidRepository.findById(bidId).orElseThrow(EntityNotFoundException::new);
        return BidMapper.entityToDto(bidEntity);
    }

    @Override
    @Transactional
    public void process(Long bidId) {
        BidEntity bidEntity = bidRepository.findById(bidId).orElseThrow(EntityNotFoundException::new);
        kafkaProducer.sendMessage(get(bidId));
        log.info("Bid message sending for bidId {}", bidId);
        bidEntity.setSentForRepair(true);
    }
}
