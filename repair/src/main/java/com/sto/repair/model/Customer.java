package com.sto.repair.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
}
