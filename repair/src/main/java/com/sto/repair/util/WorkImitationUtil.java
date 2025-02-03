package com.sto.repair.util;

import com.sto.repair.model.constant.RepairStatus;
import com.sto.repair.model.entity.BidEntity;
import com.sto.repair.model.entity.IssueCodeEntity;
import com.sto.repair.repository.BidRepository;
import com.sto.repair.repository.IssueCodeRepository;
import com.sto.repair.service.ActiveMQProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class WorkImitationUtil {
    private final BidRepository bidRepository;
    private final ActiveMQProducer activeMQProducer;
    private final FinishRepairEventPublisher finishRepairEventPublisher;

    public void startWorkImitation(Long bidId) {
        log.info("BidId {} starting repair", bidId);
        if (!bidRepository.existsById(bidId)) {
            log.error("BidId {} not found in db", bidId);
            return;
        }
        BidEntity bidEntity = bidRepository.findById(bidId).get();
        bidEntity.setStatus(RepairStatus.PROCESSING);
        bidRepository.save(bidEntity);
        //Imitation of work
        ////Adding or removing +- 2 seconds. Imitating that work completed earlier or later estimated time
        Set<IssueCodeEntity> issues = bidRepository.findIssuesById(bidEntity.getId());
        long estimatedCompleteTimeInSec = issues.stream()
                .map(IssueCodeEntity::getRepairTimeInHours)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(60))
                .multiply(BigDecimal.valueOf(60))
                .longValue();
        Random random = new Random();
        int randomNumber = random.nextInt(5) - 2;
        ////must be not negative
        long delayInMs = (estimatedCompleteTimeInSec - 2L > 0  ?
                estimatedCompleteTimeInSec - randomNumber  :
                estimatedCompleteTimeInSec) * 1000;
        activeMQProducer.sendMessageWithDelay(bidEntity.getId(), delayInMs);
    }

    public void endWorkImitation(Long bidId) {
        if (!bidRepository.existsById(bidId)) {
            log.error("BidId {} not found in db", bidId);
            return;
        }
        BidEntity bidEntity = bidRepository.findById(bidId).get();
        log.info("BidId {} successfully processed", bidEntity.getId());
        bidEntity.setStatus(RepairStatus.DONE);
        bidEntity.setRepairFinishedDt(LocalDateTime.now());
        bidRepository.save(bidEntity);
        finishRepairEventPublisher.publishEvent(bidId);
    }
}
