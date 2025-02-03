package com.sto.check.model.entity;

import com.sto.check.model.constant.RepairStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BidEntity {
    @Id
    private Long id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RepairStatus status;
    @Column(name = "complaint", nullable = false)
    private String complaint;
    @Column(name = "created_dt", nullable = false, updatable = false)
    private LocalDateTime createdDt;
    @Column(name = "preferred_dt")
    private LocalDate preferredDt;
    @Column(name = "estimated_repair_dt", nullable = false)
    private LocalDate estimatedRepairDt;
    @Column(name = "repair_received_dt", nullable = false, updatable = false)
    private LocalDateTime repairReceivedDt;
    @Column(name = "repair_finished_dt", updatable = false)
    private LocalDateTime repairFinishedDt;
    private String notes;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "bid", fetch = FetchType.LAZY)
    private Set<AttachmentEntity> attachments;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "bid_issue_codes",
            joinColumns = @JoinColumn(name = "bid_id"),
            inverseJoinColumns = @JoinColumn(name = "issue_code"))
    private Set<IssueCodeEntity> issueCodes;
}
