package com.sto.bid.model.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidRequest {
    @NotNull(message = "Данные клиента должны быть заполнены")
    private Long customerId;
    @NotNull(message = "Данные транспорта должны быть заполнены")
    private Long vehicleId;
    @NotBlank(message = "Данные о жалобе не должно быть пустым")
    private String complaint;
    private LocalDate preferredDate;
    private List<AttachmentRequest> attachments = new ArrayList<>();
    private String notes;

}
