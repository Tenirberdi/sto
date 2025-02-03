package com.sto.check.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VehicleEntity {
    @Id
    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String vin;
    private Integer mileage;
    @Column(name = "license_plate", unique = true)
    private String licensePlate;
    @Column(name = "engine_type")
    private String engineType;
    @OneToMany(mappedBy="vehicle")
    private List<BidEntity> bids;
}
