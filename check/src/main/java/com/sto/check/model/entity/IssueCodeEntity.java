package com.sto.check.model.entity;

import com.sto.check.model.constant.IssueCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "issue_codes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IssueCodeEntity {
    @Id
    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private IssueCode code;
    @Column(name = "description")
    private String description;
    @Column(name = "repair_time_in_hours")
    private BigDecimal repairTimeInHours;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @ManyToMany(mappedBy = "issueCodes")
    private List<BidEntity> bids;
}
