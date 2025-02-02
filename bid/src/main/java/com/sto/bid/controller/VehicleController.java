package com.sto.bid.controller;

import com.sto.bid.model.rest.Vehicle;
import com.sto.bid.model.rest.request.VehicleRequest;
import com.sto.bid.model.rest.response.RestResponse;
import com.sto.bid.service.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("/vehicles")
    public RestResponse<Map<String, Long>> createVehicle(@RequestBody @Valid VehicleRequest vehicleRequest) {
        return RestResponse.success(Map.of("vehicleId", vehicleService.save(vehicleRequest)));
    }

    @GetMapping("/vehicles/{id}")
    public RestResponse<Vehicle> getVehicle(@PathVariable Long id) {
        return RestResponse.success(vehicleService.get(id));
    }

}
