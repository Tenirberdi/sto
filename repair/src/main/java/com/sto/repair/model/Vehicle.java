package com.sto.repair.model;

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
