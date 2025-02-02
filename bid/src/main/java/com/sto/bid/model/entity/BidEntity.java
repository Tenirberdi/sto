package com.sto.bid.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;
    @Column(name = "sent_for_repair")
    private Boolean sentForRepair = false;
    @Column(name = "complaint", nullable = false)
    private String complaint;
    @Column(name = "created_dt", nullable = false, updatable = false)
    private LocalDateTime createdDt;
    @Column(name = "preferred_dt")
    private LocalDate preferredDt;
    private String notes;
    @OneToMany(mappedBy = "bid", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<AttachmentEntity> attachments;
}
