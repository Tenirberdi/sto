package com.sto.repair.service.impl;

import com.sto.repair.exception.EntityNotFoundException;
import com.sto.repair.model.Bid;
import com.sto.repair.model.Issue;
import com.sto.repair.model.constant.IssueCode;
import com.sto.repair.model.constant.RepairStatus;
import com.sto.repair.model.entity.BidEntity;
import com.sto.repair.repository.AttachmentRepository;
import com.sto.repair.repository.BidRepository;
import com.sto.repair.repository.IssueCodeRepository;
import com.sto.repair.service.KafkaProducer;
import com.sto.repair.service.RepairService;
import com.sto.repair.util.AttachmentMapper;
import com.sto.repair.util.BidMapper;
import com.sto.repair.util.WorkImitationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {
    private final AttachmentRepository attachmentRepository;
    private final IssueCodeRepository issueCodeRepository;
    private final BidRepository bidRepository;
    private final WorkImitationUtil workImitationUtil;
    private final KafkaProducer kafkaProducer;

    @Override
    public void startOfRepair(Bid bid) {
        if (bidRepository.existsById(bid.getId())) {
            log.error("BidId {} already in repair", bid.getId());
            return;
        }
        bid.setRepairReceivedDt(LocalDateTime.now());
        findIssues(bid);
        startRepair(bid);
    }

    @Override
    public void endOfRepair(Long bidId) {
        log.info("Repair of bidId {} finished and sending to form check", bidId);
        if (!bidRepository.existsById(bidId)) {
            log.error("BidId {} not found in db", bidId);
            return;
        }
        BidEntity bidEntity = bidRepository.findById(bidId).get();
        Bid bid = BidMapper.entityToDto(bidEntity);
        kafkaProducer.sendMessage(bid);
    }

    @Override
    public Bid getRepairStatus(Long bidId) {
        if (!bidRepository.existsById(bidId)) {
            log.error("BidId {} not found", bidId);
            throw new EntityNotFoundException();
        }
        return BidMapper.entityToDto(bidRepository.findById(bidId).get());
    }

    private void findIssues(Bid vehicleData) {
        //finding random issues in vehicle
        log.info("Analyzing issues of vehicle in bidId: {}", vehicleData.getId());
        Random random = new Random();
        Set<IssueCode> issueCodes = Arrays.stream(IssueCode.values())
                .unordered()
                .skip(random.nextInt(IssueCode.values().length))
                .limit(random.nextInt(IssueCode.values().length) + 1)
                .collect(Collectors.toSet());
        vehicleData.setIssues(issueCodeRepository.findAllById(issueCodes)
                .stream()
                .map(i -> new Issue(i.getCode(), i.getDescription(), i.getRepairTimeInHours()))
                .collect(Collectors.toSet()));
        vehicleData.setEstimatedRepairDt(vehicleData
                .getRepairReceivedDt()
                .plusDays(vehicleData.getIssues().stream().map(Issue::getRepairTimeInHours).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(24L), 2, BigDecimal.ROUND_CEILING).longValue())
                .toLocalDate());
    }

    private void startRepair(Bid bid) {
        log.info("Starting repair process for bidId: {}", bid.getId());
        bid.setStatus(RepairStatus.ACCEPTED);
        BidEntity bidEntity = bidRepository.save(BidMapper.dtoToEntity(bid));
        attachmentRepository.saveAll(bid.getAttachments()
                .stream()
                .map(AttachmentMapper::dtoToEntity)
                .peek(a -> a.setBid(bidEntity))
                .collect(Collectors.toList()));
        workImitationUtil.startWorkImitation(bid.getId());
    }
}
