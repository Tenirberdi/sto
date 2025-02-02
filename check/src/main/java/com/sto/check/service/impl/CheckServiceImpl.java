package com.sto.check.service.impl;

import com.sto.check.exception.EntityNotFoundException;
import com.sto.check.model.Bid;
import com.sto.check.model.Issue;
import com.sto.check.model.constant.IssueCode;
import com.sto.check.model.entity.BidEntity;
import com.sto.check.model.entity.IssueCodeEntity;
import com.sto.check.repository.AttachmentRepository;
import com.sto.check.repository.BidRepository;
import com.sto.check.repository.IssueCodeRepository;
import com.sto.check.service.CheckService;
import com.sto.check.util.AttachmentMapper;
import com.sto.check.util.BidMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final IssueCodeRepository issueCodeRepository;
    private final BidRepository bidRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public void formCheck(Bid bid) {
        if (bidRepository.existsById(bid.getId())) {
            log.error("For bidId {} already formed check", bid.getId());
            return;
        }
        calculatePrice(bid);
        BidEntity bidEntity = bidRepository.save(BidMapper.dtoToEntity(bid));
        attachmentRepository.saveAll(bid.getAttachments()
                .stream()
                .map(AttachmentMapper::dtoToEntity)
                .peek(a -> a.setBid(bidEntity))
                .collect(Collectors.toList()));
        log.info("BidId {} successfully formed", bid.getId());
    }

    @Override
    public Bid getCheck(Long bidId) {
        if (!bidRepository.existsById(bidId)) {
            log.error("For bidId {} already formed check", bidId);
            throw new EntityNotFoundException();
        }
        return BidMapper.entityToDto(bidRepository.findById(bidId).get());
    }

    private void calculatePrice(Bid bid) {
        Map<IssueCode, BigDecimal> prices = issueCodeRepository.findAllById(bid.getIssues().stream()
                .map(Issue::getCode)
                .collect(Collectors.toList())).stream().collect(Collectors.toMap(IssueCodeEntity::getCode, IssueCodeEntity::getPrice));
        bid.setIssues(bid.getIssues().stream().peek(i -> i.setPrice(prices.get(i.getCode()))).collect(Collectors.toSet()));

        bid.setTotalPrice(bid.getIssues().stream().map(Issue::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
