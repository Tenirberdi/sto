package com.sto.repair.repository;

import com.sto.repair.model.entity.BidEntity;
import com.sto.repair.model.entity.IssueCodeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BidRepository extends JpaRepository<BidEntity, Long> {
    @EntityGraph(attributePaths = {"customer", "vehicle", "attachments", "issueCodes"})
    Optional<BidEntity> findWithAllById(Long id);
    @Query("select b.issueCodes from BidEntity b where b.id = :bidId")
    Set<IssueCodeEntity> findIssuesById(Long bidId);
}
