package com.sto.bid.service;

import com.sto.bid.model.rest.Vehicle;
import com.sto.bid.model.rest.request.VehicleRequest;

public interface VehicleService {
    Long save(VehicleRequest vehicleRequest);
    Vehicle get(Long vehicleId);
}
