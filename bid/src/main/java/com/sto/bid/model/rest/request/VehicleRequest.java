package com.sto.bid.model.rest.request;

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
public class VehicleRequest {
    @NotBlank(message = "Данные производства не должны быть пустыми")
    private String make;
    @NotBlank(message = "Данные о модели транспорта не должно быть пустым")
    private String model;
    @NotNull(message = "Год выпуска транспорта не должно быть пустым")
    private Integer year;
    private String vin;
    private Integer mileage;
    @NotBlank(message = "Гос номер транспорта не должен быть пустым")
    private String licensePlate;
    private String engineType;
}
