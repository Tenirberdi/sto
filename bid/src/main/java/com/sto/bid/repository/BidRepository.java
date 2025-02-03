package com.sto.bid.repository;

import com.sto.bid.model.entity.BidEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<BidEntity, Long> {
    @EntityGraph(attributePaths = {"customer", "vehicle", "attachments"})
    Optional<BidEntity> findWithAllById(Long id);
}
