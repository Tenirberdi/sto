package com.sto.repair.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    private Long id;
    private String fileName;
    private String fileType;
    private String url;
}
