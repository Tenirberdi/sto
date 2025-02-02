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
public class AttachmentRequest {
    @NotBlank(message = "Название прикрепляемого элемента не должно быть пустым")
    private String fileName;
    @NotBlank(message = "Формат приклепляемого элемента не должен быть пустым")
    private String fileType;
    @NotBlank(message = "УРЛ прикрепляемого элемента не должен быть пустым")
    private String url;
}
