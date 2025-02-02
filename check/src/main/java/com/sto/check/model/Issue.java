package com.sto.check.model;


import com.sto.check.model.constant.IssueCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private IssueCode code;
    private String description;
    private BigDecimal repairTimeInHours;
    private BigDecimal price;
}
