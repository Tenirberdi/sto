package com.sto.bid.model.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String vin;
    private Integer mileage;
    private String licensePlate;
    private String engineType;
}
