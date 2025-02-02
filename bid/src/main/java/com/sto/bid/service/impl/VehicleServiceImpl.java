package com.sto.bid.service.impl;

import com.sto.bid.exception.EntityNotFoundException;
import com.sto.bid.exception.UniqueConstraintError;
import com.sto.bid.model.entity.VehicleEntity;
import com.sto.bid.model.rest.Vehicle;
import com.sto.bid.model.rest.request.VehicleRequest;
import com.sto.bid.repository.VehicleRepository;
import com.sto.bid.service.VehicleService;
import com.sto.bid.util.VehicleMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    @Override
    public Long save(VehicleRequest vehicleRequest) {
        if (vehicleRepository.existsByLicensePlate(vehicleRequest.getLicensePlate())) {
            throw new UniqueConstraintError();
        }
        VehicleEntity vehicleEntity = vehicleRepository.save(VehicleMapper.dtoToEntity(vehicleRequest));
        return vehicleEntity.getId();
    }

    @Override
    public Vehicle get(Long vehicleId) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleId).orElseThrow(EntityNotFoundException::new);
        return VehicleMapper.entityToDto(vehicleEntity);
    }
}
