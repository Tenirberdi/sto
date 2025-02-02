package com.sto.bid.util;

import com.sto.bid.model.entity.VehicleEntity;
import com.sto.bid.model.rest.Vehicle;
import com.sto.bid.model.rest.request.VehicleRequest;

public class VehicleMapper {
    public static VehicleEntity dtoToEntity(VehicleRequest vehicleRequest) {
        return VehicleEntity.builder()
                .make(vehicleRequest.getMake())
                .model(vehicleRequest.getModel())
                .year(vehicleRequest.getYear())
                .vin(vehicleRequest.getVin())
                .mileage(vehicleRequest.getMileage())
                .licensePlate(vehicleRequest.getLicensePlate())
                .engineType(vehicleRequest.getEngineType()).build();
    }

    public static Vehicle entityToDto(VehicleEntity vehicleEntity) {
        return Vehicle.builder()
                .id(vehicleEntity.getId())
                .make(vehicleEntity.getMake())
                .model(vehicleEntity.getModel())
                .year(vehicleEntity.getYear())
                .vin(vehicleEntity.getVin())
                .mileage(vehicleEntity.getMileage())
                .licensePlate(vehicleEntity.getLicensePlate())
                .engineType(vehicleEntity.getVin()).build();
    }
}
