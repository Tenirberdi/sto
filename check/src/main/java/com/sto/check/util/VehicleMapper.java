package com.sto.check.util;

import com.sto.check.model.Vehicle;
import com.sto.check.model.entity.VehicleEntity;

public class VehicleMapper {
    public static VehicleEntity dtoToEntity(Vehicle vehicle) {
        return VehicleEntity.builder()
                .id(vehicle.getId())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .vin(vehicle.getVin())
                .mileage(vehicle.getMileage())
                .licensePlate(vehicle.getLicensePlate())
                .engineType(vehicle.getEngineType()).build();
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
