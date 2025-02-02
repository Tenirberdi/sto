package com.sto.bid.model.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @NotBlank(message = "Имя клиента не должно быть пустым")
    private String firstName;
    @NotBlank(message = "Фамилие клиента не должно быть пустым")
    private String lastName;
    private String middleName;
    @NotBlank(message = "Контактные данные клиента не должно быть пустым")
    private String phone;
}
