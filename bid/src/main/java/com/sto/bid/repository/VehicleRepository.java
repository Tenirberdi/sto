package com.sto.bid.repository;

import com.sto.bid.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    boolean existsByLicensePlate(String licencePlate);
}
