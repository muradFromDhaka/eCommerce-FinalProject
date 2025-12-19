package com.example.PracticeCRUD.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.PracticeCRUD.Enum.PayoutStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "vendor_payouts")
public class VendorPayout extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PayoutStatus status;

    private LocalDateTime payoutDate;
}

