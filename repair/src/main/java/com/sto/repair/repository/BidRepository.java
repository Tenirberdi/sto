package com.sto.repair.repository;

import com.sto.repair.model.entity.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<BidEntity, Long> {
}
